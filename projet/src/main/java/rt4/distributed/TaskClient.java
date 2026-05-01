package rt4.distributed;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.internal.DnsNameResolverProvider;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TaskClient {

    // Client knows all nodes
    private static final List<String> NODE_IDS = List.of("node1", "node2", "node3");
    private static final List<Integer> NODE_PORTS = List.of(50051, 50052, 50053);

    public static void main(String[] args) throws Exception {
        System.out.println("[Client] Waiting for nodes to start...");
        Thread.sleep(5000);

        // Send 5 tasks, each to a randomly chosen node
        for (int i = 0; i < 5; i++) {
            sendTask("compute", "payload-" + i);
            Thread.sleep(500);
        }

        // A couple of message tasks
        sendTask("message", "Hello from distributed client!");
        sendTask("message", "Another message task");

        System.out.println("[Client] All tasks submitted.");
    }

    private static void sendTask(String taskType, String payload) {
        // Pick a random node — the node will handle leader election internally
        Random rng = new Random();
        int idx = rng.nextInt(NODE_IDS.size());
        String targetNode = NODE_IDS.get(idx);
        int targetPort = NODE_PORTS.get(idx);

        String taskId = UUID.randomUUID().toString().substring(0, 8);

        System.out.println("[Client] Sending task " + taskId
                + " (type=" + taskType + ") to " + targetNode + ":" + targetPort);

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(targetNode, targetPort)
                .nameResolverFactory(new DnsNameResolverProvider()) // force DNS via system
                .usePlaintext()
                .build();

        try {
            NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(channel);

            TaskProto.TaskResponse response = stub.submitTask(
                    TaskProto.TaskRequest.newBuilder()
                            .setTaskId(taskId)
                            .setTaskType(taskType)
                            .setPayload(payload)
                            .build());

            System.out.println("[Client] Response for " + taskId
                    + ": success=" + response.getSuccess()
                    + ", result=" + response.getResult()
                    + ", executedBy=" + response.getExecutedBy());

        } catch (Exception e) {
            System.err.println("[Client] Error sending task " + taskId + ": " + e.getMessage());
        } finally {
            channel.shutdown();
            try {
                channel.awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
