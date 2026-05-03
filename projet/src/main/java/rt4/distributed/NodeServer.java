package rt4.distributed;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NodeServer {

    private static final Logger log = LoggerFactory.getLogger(NodeServer.class);

    // HTTP ports = gRPC port - 7000  (50051 → 43051, 50052 → 43052, 50053 → 43053)
    private static final int HTTP_PORT_OFFSET = -7000;

    // Thread pool for SSE streaming (one thread per connected SSE client)
    private static final ExecutorService sseExecutor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: NodeServer <nodeId> <grpcPort>");
            System.exit(1);
        }

        String nodeId   = args[0];
        int    grpcPort = Integer.parseInt(args[1]);
        int    httpPort = grpcPort + HTTP_PORT_OFFSET;

        // ── Start gRPC server ─────────────────────────────────────────────────
        NodeServiceImpl service = new NodeServiceImpl(nodeId, grpcPort);

        Server grpcServer = ServerBuilder.forPort(grpcPort)
                .addService(service)
                .build()
                .start();

        log.info("[{}] gRPC server started on port {}", nodeId, grpcPort);

        // ── Start HTTP server (status + SSE events for dashboard) ─────────────
        Javalin http = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                    it.allowCredentials = false;
                });
            });
        });

        // ── /status — polled every 2 s by the dashboard ───────────────────────
        http.get("/status", ctx -> {
            ctx.json(Map.of(
                    "nodeId",        nodeId,
                    "grpcPort",      grpcPort,
                    "isLeader",      service.isLeader(),
                    "currentLeader", service.getCurrentLeader() != null ? service.getCurrentLeader() : "unknown",
                    "aliveNodes",    service.getAliveNodes(),
                    "currentTurn",   service.isLeader() ? service.getCurrentTurnNode() : "—"
            ));
        });

        http.get("/health", ctx -> ctx.result("ok"));

        // ── /events — SSE endpoint for real-time activity feed ────────────────
       http.sse("/events", client -> {
            client.keepAlive();
            try {
                while (true) {
                    String event = service.pollEvent(5000);
                    if (event != null) {
                        client.sendEvent(event);
                    }
                    if (client.terminated()) break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        http.start("0.0.0.0", httpPort);
        log.info("[{}] HTTP status + SSE endpoint started on port {}", nodeId, httpPort);

        // ── Block until gRPC shutdown ─────────────────────────────────────────
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("[{}] Shutting down...", nodeId);
            grpcServer.shutdown();
            http.stop();
            sseExecutor.shutdownNow();
        }));

        grpcServer.awaitTermination();
    }
}