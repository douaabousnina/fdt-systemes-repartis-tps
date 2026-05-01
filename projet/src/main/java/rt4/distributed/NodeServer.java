package rt4.distributed;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class NodeServer {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: NodeServer <nodeId> <port>");
            System.exit(1);
        }

        String nodeId = args[0]; // e.g. "node1"
        int port = Integer.parseInt(args[1]); // e.g. 50051

        Server server = ServerBuilder.forPort(port)
                .addService(new NodeServiceImpl(nodeId, port))
                .build()
                .start();

        System.out.println("[" + nodeId + "] gRPC server started on port " + port);
        System.out.println("[" + nodeId + "] Leader is elected dynamically (smallest ID wins).");

        server.awaitTermination();
    }
}
