package rt4.distributed;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.grpc.internal.DnsNameResolverProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class NodeServiceImpl extends NodeServiceGrpc.NodeServiceImplBase {

    private final String nodeId;
    private final int port;

    private static final List<String> NODE_IDS = List.of("node1", "node2", "node3");
    private static final List<Integer> NODE_PORTS = List.of(50051, 50052, 50053);

    // Distributed mutex — one lock per node instance
    private final AtomicBoolean lockHeld = new AtomicBoolean(false);
    private volatile String lockOwner = null;

    public NodeServiceImpl(String nodeId, int port) {
        this.nodeId = nodeId;
        this.port = port;
    }

    // ─── Called by client ────────────────────────────────────────────────────

    @Override
    public void submitTask(TaskProto.TaskRequest request,
            StreamObserver<TaskProto.TaskResponse> responseObserver) {
        try {
            System.out.println("[" + nodeId + "] Received task: " + request.getTaskId()
                    + " type=" + request.getTaskType());

            String leaderId = electLeader();

            TaskProto.TaskResponse result;
            if (nodeId.equals(leaderId)) {
                result = handleAsLeader(request);
            } else {
                System.out.println("[" + nodeId + "] Not leader — forwarding to " + leaderId);
                result = forwardToLeader(leaderId, request);
            }

            responseObserver.onNext(result);
            responseObserver.onCompleted();

        } catch (Exception e) {
            System.err.println("[" + nodeId + "] submitTask error: " + e.getMessage());
            e.printStackTrace();
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    // ─── Called by leader to delegate here ───────────────────────────────────

    @Override
    public void forwardTask(TaskProto.TaskRequest request,
            StreamObserver<TaskProto.TaskResponse> responseObserver) {
        try {
            System.out.println("[" + nodeId + "] Executing forwarded task: " + request.getTaskId());
            TaskProto.TaskResponse result = handleAsLeader(request);
            responseObserver.onNext(result);
            responseObserver.onCompleted();
        } catch (Exception e) {
            System.err.println("[" + nodeId + "] forwardTask error: " + e.getMessage());
            e.printStackTrace();
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    // ─── Mutex RPCs ───────────────────────────────────────────────────────────

    @Override
    public void acquireLock(TaskProto.LockRequest request,
            StreamObserver<TaskProto.LockResponse> responseObserver) {
        try {
            boolean acquired = lockHeld.compareAndSet(false, true);
            if (acquired) {
                lockOwner = request.getRequesterNode();
                System.out.println("[" + nodeId + "] Lock GRANTED to " + lockOwner
                        + " for task " + request.getTaskId());
            } else {
                System.out.println("[" + nodeId + "] Lock DENIED for " + request.getRequesterNode()
                        + " (held by " + lockOwner + ")");
            }
            responseObserver.onNext(TaskProto.LockResponse.newBuilder()
                    .setGranted(acquired)
                    .setMessage(acquired ? "Lock granted" : "Held by " + lockOwner)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void releaseLock(TaskProto.LockRequest request,
            StreamObserver<TaskProto.LockResponse> responseObserver) {
        try {
            lockHeld.set(false);
            lockOwner = null;
            System.out.println("[" + nodeId + "] Lock RELEASED by " + request.getRequesterNode());
            responseObserver.onNext(TaskProto.LockResponse.newBuilder()
                    .setGranted(true).setMessage("Released").build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    // ─── Internal logic ───────────────────────────────────────────────────────

    /** Smallest node ID wins — deterministic, no communication needed. */
    private String electLeader() {
        return NODE_IDS.stream().min(String::compareTo).orElse(nodeId);
    }

    /** Leader acquires lock on all nodes, executes, releases. */
    private TaskProto.TaskResponse handleAsLeader(TaskProto.TaskRequest request) {
        System.out.println("[" + nodeId + "] Acquiring distributed lock for task " + request.getTaskId());

        boolean allGranted = broadcastAcquireLock(request.getTaskId());
        if (!allGranted) {
            System.out.println("[" + nodeId + "] Lock acquisition failed.");
            return TaskProto.TaskResponse.newBuilder()
                    .setSuccess(false)
                    .setResult("Lock not available — another task is running.")
                    .setExecutedBy(nodeId)
                    .build();
        }

        String result;
        try {
            System.out.println("[" + nodeId + "] Executing task " + request.getTaskId());
            result = executeTask(request);
        } finally {
            broadcastReleaseLock(request.getTaskId());
        }

        return TaskProto.TaskResponse.newBuilder()
                .setSuccess(true)
                .setResult(result)
                .setExecutedBy(nodeId)
                .build();
    }

    /** Simulate work. Replace with real logic as needed. */
    private String executeTask(TaskProto.TaskRequest request) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String type = request.getTaskType();
        String payload = request.getPayload();

        if ("compute".equals(type)) {
            return "Result: " + (payload.length() * 42);
        } else if ("message".equals(type)) {
            return "Echo: " + payload;
        } else {
            return "Unknown task type: " + type;
        }
    }

    /**
     * Acquires lock on every node.
     * Self-lock is handled locally (avoids loopback network issues in Docker).
     * Returns false immediately if any node denies.
     */
    private boolean broadcastAcquireLock(String taskId) {
        for (int i = 0; i < NODE_IDS.size(); i++) {
            String target = NODE_IDS.get(i);
            int tport = NODE_PORTS.get(i);

            // Handle self locally — no network call
            if (target.equals(nodeId)) {
                boolean ok = lockHeld.compareAndSet(false, true);
                if (!ok) {
                    System.err.println("[" + nodeId + "] Self-lock failed (already held).");
                    return false;
                }
                lockOwner = nodeId;
                continue;
            }

            ManagedChannel ch = ManagedChannelBuilder.forAddress(target, tport)
                    .nameResolverFactory(new DnsNameResolverProvider()) // force DNS via system
                    .usePlaintext().build();
            try {
                NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(ch)
                        .withDeadlineAfter(3, TimeUnit.SECONDS);
                TaskProto.LockResponse resp = stub.acquireLock(
                        TaskProto.LockRequest.newBuilder()
                                .setTaskId(taskId)
                                .setRequesterNode(nodeId)
                                .build());
                if (!resp.getGranted()) {
                    System.err.println("[" + nodeId + "] Lock denied by " + target);
                    ch.shutdown();
                    return false;
                }
            } catch (Exception e) {
                System.err.println("[" + nodeId + "] Could not reach " + target
                        + " for lock: " + e.getMessage());
                ch.shutdown();
                return false;
            }
            ch.shutdown();
        }
        return true;
    }

    /** Releases lock on every node. Best-effort — logs failures but continues. */
    private void broadcastReleaseLock(String taskId) {
        for (int i = 0; i < NODE_IDS.size(); i++) {
            String target = NODE_IDS.get(i);
            int tport = NODE_PORTS.get(i);

            if (target.equals(nodeId)) {
                lockHeld.set(false);
                lockOwner = null;
                continue;
            }

            ManagedChannel ch = ManagedChannelBuilder.forAddress(target, tport)
                    .nameResolverFactory(new DnsNameResolverProvider()) // force DNS via system
                    .usePlaintext().build();
            try {
                NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(ch)
                        .withDeadlineAfter(3, TimeUnit.SECONDS);
                stub.releaseLock(TaskProto.LockRequest.newBuilder()
                        .setTaskId(taskId)
                        .setRequesterNode(nodeId)
                        .build());
            } catch (Exception e) {
                System.err.println("[" + nodeId + "] Release failed on " + target
                        + ": " + e.getMessage());
            }
            ch.shutdown();
        }
    }

    /** Forwards the task to the leader node over gRPC. */
    private TaskProto.TaskResponse forwardToLeader(String leaderId, TaskProto.TaskRequest request) {
        int leaderPort = NODE_PORTS.get(NODE_IDS.indexOf(leaderId));

        ManagedChannel ch = ManagedChannelBuilder.forAddress(leaderId, leaderPort)
                .nameResolverFactory(new DnsNameResolverProvider()) // force DNS via system
                .usePlaintext().build();
        try {
            NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(ch)
                    .withDeadlineAfter(10, TimeUnit.SECONDS);
            return stub.forwardTask(request);
        } catch (Exception e) {
            System.err.println("[" + nodeId + "] Forward to leader failed: " + e.getMessage());
            return TaskProto.TaskResponse.newBuilder()
                    .setSuccess(false)
                    .setResult("Forward failed: " + e.getMessage())
                    .setExecutedBy(nodeId)
                    .build();
        } finally {
            ch.shutdown();
        }
    }
}