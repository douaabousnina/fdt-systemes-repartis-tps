package rt4.distributed;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.grpc.internal.DnsNameResolverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.ReentrantLock;

public class NodeServiceImpl extends NodeServiceGrpc.NodeServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(NodeServiceImpl.class);

    // ─── Cluster config ──────────────────────────────────────────────────────
    private final String nodeId;
    private final int port;

    private static final List<String> NODE_IDS   = List.of("node1", "node2", "node3");
    private static final List<Integer> NODE_PORTS = List.of(50051, 50052, 50053);

    // ─── Fault tolerance: alive nodes (updated by heartbeat thread) ──────────
    private final Set<String> aliveNodes = ConcurrentHashMap.newKeySet();

    // ─── Paxos acceptor state ────────────────────────────────────────────────
    private final Object paxosLock = new Object();
    private volatile int    promisedId    = -1;
    private volatile int    acceptedId    = -1;
    private volatile String acceptedValue = null;

    // ─── Paxos proposer state ────────────────────────────────────────────────
    private volatile String currentLeader = null;
    private final AtomicInteger proposalCounter = new AtomicInteger(0);

    // ─── Leader-local mutex ──────────────────────────────────────────────────
    private final ReentrantLock taskLock = new ReentrantLock(true); // fair

    // ─── SSE event bus ───────────────────────────────────────────────────────
    // A bounded queue of JSON event strings. NodeServer reads from this to push
    // to connected SSE clients. Cap at 500 so a slow/absent dashboard can't
    // grow memory unbounded.
    private final BlockingQueue<String> eventQueue = new LinkedBlockingQueue<>(500);

    /**
     * Emit a structured JSON event onto the queue.
     * Drop silently if the queue is full (dashboard is not consuming fast enough).
     *
     * @param type   one of: task-submit, forward, paxos, execute, result,
     *               heartbeat, fault, mutex-acquire, mutex-release
     * @param actor  nodeId or "client"
     * @param msg    human-readable description
     */
    public void emit(String type, String actor, String msg) {
        String json = String.format(
            "{\"type\":\"%s\",\"actor\":\"%s\",\"msg\":\"%s\",\"ts\":%d}",
            type, actor, msg.replace("\"", "'"), System.currentTimeMillis()
        );
        // offer() is non-blocking; drops if full
        eventQueue.offer(json);
    }

    /** NodeServer calls this to drain the next event (blocks up to 5 s). */
    public String pollEvent(long timeoutMs) throws InterruptedException {
        return eventQueue.poll(timeoutMs, TimeUnit.MILLISECONDS);
    }

    // ─── Constructor ─────────────────────────────────────────────────────────

    public NodeServiceImpl(String nodeId, int port) {
        this.nodeId = nodeId;
        this.port   = port;

        aliveNodes.addAll(NODE_IDS);
        startHeartbeatThread();
    }

    // =========================================================================
    // CLIENT-FACING RPC
    // =========================================================================

    @Override
    public void submitTask(TaskProto.TaskRequest request,
                           StreamObserver<TaskProto.TaskResponse> responseObserver) {
        log.info("[{}] SubmitTask received taskId={} type={}", nodeId, request.getTaskId(), request.getTaskType());
        emit("task-submit", nodeId,
            "received task " + request.getTaskId() + " [" + request.getTaskType() + "] from client");
        try {
            String leaderId = getOrElectLeader();
            log.info("[{}] Current leader = {}", nodeId, leaderId);

            TaskProto.TaskResponse result;
            if (nodeId.equals(leaderId)) {
                result = handleAsLeader(request);
            } else {
                log.info("[{}] Not leader — forwarding to {}", nodeId, leaderId);
                emit("forward", nodeId,
                    "forwarding task " + request.getTaskId() + " to leader " + leaderId);
                result = forwardToLeader(leaderId, request);
            }

            emit("result", nodeId,
                "task " + request.getTaskId() + " done → " + result.getResult());

            responseObserver.onNext(result);
            responseObserver.onCompleted();

        } catch (Exception e) {
            log.error("[{}] submitTask error: {}", nodeId, e.getMessage(), e);
            emit("fault", nodeId, "submitTask error: " + e.getMessage());
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    // =========================================================================
    // INTER-NODE: TASK ROUTING
    // =========================================================================

    @Override
    public void forwardTask(TaskProto.TaskRequest request,
                            StreamObserver<TaskProto.TaskResponse> responseObserver) {
        log.info("[{}] ForwardTask received taskId={}", nodeId, request.getTaskId());
        emit("forward", nodeId,
            "received forwarded task " + request.getTaskId() + " — executing as leader");
        try {
            TaskProto.TaskResponse result = handleAsLeader(request);
            responseObserver.onNext(result);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("[{}] forwardTask error: {}", nodeId, e.getMessage(), e);
            emit("fault", nodeId, "forwardTask error: " + e.getMessage());
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    // =========================================================================
    // PAXOS — ACCEPTOR SIDE
    // =========================================================================

    @Override
    public void prepare(TaskProto.PrepareRequest request,
                        StreamObserver<TaskProto.PromiseResponse> responseObserver) {
        synchronized (paxosLock) {
            boolean promise = request.getProposalId() > promisedId;
            log.info("[{}] Paxos Prepare proposalId={} promised={}", nodeId, request.getProposalId(), promise);

            if (promise) {
                promisedId = request.getProposalId();
                emit("paxos", nodeId,
                    "promised proposal #" + request.getProposalId() + " from " + request.getProposerId());
            }

            responseObserver.onNext(TaskProto.PromiseResponse.newBuilder()
                    .setPromised(promise)
                    .setPromisedId(promisedId)
                    .setAcceptedValue(acceptedValue != null ? acceptedValue : "")
                    .build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void accept(TaskProto.AcceptRequest request,
                       StreamObserver<TaskProto.AcceptedResponse> responseObserver) {
        synchronized (paxosLock) {
            boolean accepted = request.getProposalId() >= promisedId;
            log.info("[{}] Paxos Accept proposalId={} value={} accepted={}",
                    nodeId, request.getProposalId(), request.getValue(), accepted);

            if (accepted) {
                promisedId    = request.getProposalId();
                acceptedId    = request.getProposalId();
                acceptedValue = request.getValue();
                currentLeader = request.getValue();
                emit("paxos", nodeId,
                    "accepted leader=" + request.getValue() + " (proposal #" + request.getProposalId() + ")");
            }

            responseObserver.onNext(TaskProto.AcceptedResponse.newBuilder()
                    .setAccepted(accepted)
                    .setPromisedId(promisedId)
                    .build());
            responseObserver.onCompleted();
        }
    }

    // =========================================================================
    // HEALTH CHECK
    // =========================================================================

    @Override
    public void ping(TaskProto.HealthRequest request,
                     StreamObserver<TaskProto.HealthResponse> responseObserver) {
        responseObserver.onNext(TaskProto.HealthResponse.newBuilder()
                .setAlive(true)
                .setNodeId(nodeId)
                .setCurrentLeader(currentLeader != null ? currentLeader : "")
                .build());
        responseObserver.onCompleted();
    }

    // =========================================================================
    // MUTEX RPCs — kept for proto compatibility, reflect local lock state
    // =========================================================================

    @Override
    public void requestMutex(TaskProto.MutexRequest request,
                             StreamObserver<TaskProto.MutexResponse> responseObserver) {
        boolean held = taskLock.isLocked();
        responseObserver.onNext(TaskProto.MutexResponse.newBuilder()
                .setGranted(!held)
                .setMessage(held ? "Leader mutex is currently held." : "Leader mutex is free.")
                .setCurrentTurn(0)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void releaseMutex(TaskProto.MutexRequest request,
                             StreamObserver<TaskProto.MutexResponse> responseObserver) {
        responseObserver.onNext(TaskProto.MutexResponse.newBuilder()
                .setGranted(true)
                .setMessage("Mutex managed internally by leader.")
                .setCurrentTurn(0)
                .build());
        responseObserver.onCompleted();
    }

    // =========================================================================
    // INTERNAL LOGIC
    // =========================================================================

    private String getOrElectLeader() {
        if (currentLeader != null && aliveNodes.contains(currentLeader)) {
            return currentLeader;
        }
        log.info("[{}] No valid leader — running Paxos election.", nodeId);
        emit("paxos", nodeId, "no valid leader — starting Paxos election");
        String elected = runPaxos();
        currentLeader  = elected;
        log.info("[{}] Paxos result: leader = {}", nodeId, elected);
        if (nodeId.equals(elected)) {
            emit("paxos", nodeId, "elected as LEADER via Paxos consensus");
        } else {
            emit("paxos", nodeId, "Paxos consensus reached: leader = " + elected);
        }
        return elected;
    }

    private String runPaxos() {
        int quorum = aliveNodes.size() / 2 + 1;
        int pid    = proposalCounter.incrementAndGet();

        String[] proposedValue = { nodeId };

        // ── Phase 1: Prepare ──────────────────────────────────────────────────
        List<TaskProto.PromiseResponse> promises = new ArrayList<>();

        for (int i = 0; i < NODE_IDS.size(); i++) {
            String target = NODE_IDS.get(i);
            if (!aliveNodes.contains(target)) continue;

            int tport = NODE_PORTS.get(i);

            if (target.equals(nodeId)) {
                synchronized (paxosLock) {
                    if (pid > promisedId) {
                        promisedId = pid;
                        promises.add(TaskProto.PromiseResponse.newBuilder()
                                .setPromised(true)
                                .setPromisedId(pid)
                                .setAcceptedValue(acceptedValue != null ? acceptedValue : "")
                                .build());
                    }
                }
                continue;
            }

            ManagedChannel ch = buildChannel(target, tport);
            try {
                NodeServiceGrpc.NodeServiceBlockingStub stub =
                        NodeServiceGrpc.newBlockingStub(ch).withDeadlineAfter(3, TimeUnit.SECONDS);
                TaskProto.PromiseResponse r = stub.prepare(
                        TaskProto.PrepareRequest.newBuilder()
                                .setProposalId(pid)
                                .setProposerId(nodeId)
                                .build());
                if (r.getPromised()) promises.add(r);
            } catch (Exception e) {
                log.warn("[{}] Paxos Phase1 — {} unreachable: {}", nodeId, target, e.getMessage());
                aliveNodes.remove(target);
            } finally {
                ch.shutdown();
            }
        }

        if (promises.size() < quorum) {
            log.warn("[{}] Paxos Phase1 failed — only {}/{} promises. Falling back.",
                    nodeId, promises.size(), quorum);
            return aliveNodes.stream().min(String::compareTo).orElse(nodeId);
        }

        promises.stream()
                .filter(p -> !p.getAcceptedValue().isEmpty())
                .max(Comparator.comparingInt(TaskProto.PromiseResponse::getPromisedId))
                .ifPresent(p -> {
                    proposedValue[0] = p.getAcceptedValue();
                    log.info("[{}] Paxos Phase1 — adopting prior accepted value: {} (promisedId={})",
                            nodeId, proposedValue[0], p.getPromisedId());
                });

        // ── Phase 2: Accept ───────────────────────────────────────────────────
        int acceptCount = 0;

        for (int i = 0; i < NODE_IDS.size(); i++) {
            String target = NODE_IDS.get(i);
            if (!aliveNodes.contains(target)) continue;

            int tport = NODE_PORTS.get(i);

            if (target.equals(nodeId)) {
                synchronized (paxosLock) {
                    if (pid >= promisedId) {
                        promisedId    = pid;
                        acceptedId    = pid;
                        acceptedValue = proposedValue[0];
                        currentLeader = proposedValue[0];
                        acceptCount++;
                    }
                }
                continue;
            }

            ManagedChannel ch = buildChannel(target, tport);
            try {
                NodeServiceGrpc.NodeServiceBlockingStub stub =
                        NodeServiceGrpc.newBlockingStub(ch).withDeadlineAfter(3, TimeUnit.SECONDS);
                TaskProto.AcceptedResponse r = stub.accept(
                        TaskProto.AcceptRequest.newBuilder()
                                .setProposalId(pid)
                                .setValue(proposedValue[0])
                                .build());
                if (r.getAccepted()) acceptCount++;
            } catch (Exception e) {
                log.warn("[{}] Paxos Phase2 — {} unreachable: {}", nodeId, target, e.getMessage());
                aliveNodes.remove(target);
            } finally {
                ch.shutdown();
            }
        }

        if (acceptCount >= quorum) {
            log.info("[{}] Paxos consensus reached: leader={} (pid={}, accepts={})",
                    nodeId, proposedValue[0], pid, acceptCount);
            return proposedValue[0];
        }

        log.warn("[{}] Paxos Phase2 failed — only {}/{} accepts. Falling back.", nodeId, acceptCount, quorum);
        return aliveNodes.stream().min(String::compareTo).orElse(nodeId);
    }

    private TaskProto.TaskResponse handleAsLeader(TaskProto.TaskRequest request) {
        log.info("[{}] handleAsLeader — acquiring mutex. taskId={}", nodeId, request.getTaskId());

        boolean acquired;
        try {
            acquired = taskLock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            acquired = false;
        }

        if (!acquired) {
            log.warn("[{}] Mutex not acquired within timeout. taskId={}", nodeId, request.getTaskId());
            emit("fault", nodeId, "mutex timeout for task " + request.getTaskId());
            return TaskProto.TaskResponse.newBuilder()
                    .setSuccess(false)
                    .setResult("Mutex not available — timeout.")
                    .setExecutedBy(nodeId)
                    .build();
        }

        try {
            log.info("[{}] Mutex granted — executing taskId={}", nodeId, request.getTaskId());
            emit("execute", nodeId,
                "mutex acquired — executing task " + request.getTaskId()
                + " [" + request.getTaskType() + "]");
            String result = executeTask(request);
            log.info("[{}] Mutex released — taskId={} done.", nodeId, request.getTaskId());
            emit("execute", nodeId,
                "mutex released — task " + request.getTaskId() + " complete");

            return TaskProto.TaskResponse.newBuilder()
                    .setSuccess(true)
                    .setResult(result)
                    .setExecutedBy(nodeId)
                    .build();
        } finally {
            taskLock.unlock();
        }
    }

    private String executeTask(TaskProto.TaskRequest request) {
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        return switch (request.getTaskType()) {
            case "compute" -> "Result: " + (request.getPayload().length() * 42);
            case "message" -> "Echo: " + request.getPayload();
            default        -> "Unknown task type: " + request.getTaskType();
        };
    }

    private TaskProto.TaskResponse forwardToLeader(String leaderId, TaskProto.TaskRequest request) {
        int leaderPort = NODE_PORTS.get(NODE_IDS.indexOf(leaderId));
        ManagedChannel ch = buildChannel(leaderId, leaderPort);
        try {
            return NodeServiceGrpc.newBlockingStub(ch)
                    .withDeadlineAfter(10, TimeUnit.SECONDS)
                    .forwardTask(request);
        } catch (Exception e) {
            log.error("[{}] Forward to leader {} failed: {}", nodeId, leaderId, e.getMessage());
            emit("fault", nodeId, "forward to " + leaderId + " failed: " + e.getMessage());
            currentLeader = null;
            return TaskProto.TaskResponse.newBuilder()
                    .setSuccess(false)
                    .setResult("Forward failed: " + e.getMessage())
                    .setExecutedBy(nodeId)
                    .build();
        } finally {
            ch.shutdown();
        }
    }

    // =========================================================================
    // HEARTBEAT — fault detection
    // =========================================================================

    private void startHeartbeatThread() {
        Thread hb = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try { Thread.sleep(2000); } catch (InterruptedException e) { break; }

                for (int i = 0; i < NODE_IDS.size(); i++) {
                    String target = NODE_IDS.get(i);
                    if (target.equals(nodeId)) continue;

                    int tport = NODE_PORTS.get(i);
                    ManagedChannel ch = buildChannel(target, tport);
                    try {
                        NodeServiceGrpc.NodeServiceBlockingStub stub =
                                NodeServiceGrpc.newBlockingStub(ch).withDeadlineAfter(1, TimeUnit.SECONDS);
                        TaskProto.HealthResponse r = stub.ping(
                                TaskProto.HealthRequest.newBuilder().setFromNode(nodeId).build());

                        if (r.getAlive()) {
                            boolean wasDown = aliveNodes.add(target);
                            if (wasDown) {
                                log.info("[{}] Node {} is back online.", nodeId, target);
                                emit("heartbeat", nodeId, target + " is back online");
                            }
                        }
                    } catch (Exception e) {
                        boolean wasAlive = aliveNodes.remove(target);
                        if (wasAlive) {
                            log.warn("[{}] Node {} is DOWN.", nodeId, target);
                            emit("fault", nodeId, target + " is UNREACHABLE — removed from cluster");
                            if (target.equals(currentLeader)) {
                                log.warn("[{}] Leader {} is down — forcing re-election.", nodeId, target);
                                emit("paxos", nodeId,
                                    "leader " + target + " lost — re-election will be triggered");
                                currentLeader = null;
                            }
                        }
                    } finally {
                        ch.shutdown();
                    }
                }
            }
        }, "heartbeat-" + nodeId);
        hb.setDaemon(true);
        hb.start();
    }

    // =========================================================================
    // HELPERS
    // =========================================================================

    private ManagedChannel buildChannel(String host, int port) {
        return ManagedChannelBuilder
                .forAddress(host, port)
                .nameResolverFactory(new DnsNameResolverProvider())
                .usePlaintext()
                .build();
    }

    // ── Public getters for HTTP status endpoint ──────────────────────────────

    public String getCurrentLeader()   { return currentLeader; }
    public boolean isLeader()          { return nodeId.equals(currentLeader); }
    public Set<String> getAliveNodes() { return Collections.unmodifiableSet(aliveNodes); }

    public String getCurrentTurnNode() {
        return taskLock.isLocked() ? nodeId + " (executing)" : nodeId + " (idle)";
    }
}