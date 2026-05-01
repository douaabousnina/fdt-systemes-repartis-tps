package rt4.paxos;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.internal.DnsNameResolverProvider;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PaxosAcceptor {
    public static void main(String[] args) throws Exception {
        System.out.println("Waiting for servers...");
        Thread.sleep(5000);

        // Étape 1 : Demande à chaque serveur sa valeur aléatoire
        List<String> servers = List.of("server1", "server2", "server3");
        List<Integer> ports = List.of(50051, 50052, 50053);

        Map<String, Integer> values = new HashMap<>();

        for (int i = 0; i < servers.size(); i++) {
            ManagedChannel channel = ManagedChannelBuilder
                    .forAddress(servers.get(i), ports.get(i))
                    .usePlaintext()
                    .nameResolverFactory(new DnsNameResolverProvider()) // force DNS via system
                    .build();
            PaxosServiceGrpc.PaxosServiceBlockingStub stub = PaxosServiceGrpc.newBlockingStub(channel);

            ValueResponse response = stub.proposeValue(
                    ValueRequest.newBuilder().setRequester("client").build());

            values.put(servers.get(i), response.getProposedValue());

            channel.shutdown();
        }

        // Étape 2 : Choix de la valeur maximale (R) comme valeur de consensus
        int consensusValue = Collections.max(values.values());

        System.out.println("Valeurs proposées : " + values);
        System.out.println("=> Valeur choisie par consensus : " + consensusValue);

        // Étape 3 : Envoi de cette valeur R à tous les serveurs pour qu'ils la stockent
        for (int i = 0; i < servers.size(); i++) {
            ManagedChannel channel = ManagedChannelBuilder
                    .forAddress(servers.get(i), ports.get(i))
                    .usePlaintext()
                    .nameResolverFactory(new DnsNameResolverProvider())
                    .build();

            PaxosServiceGrpc.PaxosServiceBlockingStub stub = PaxosServiceGrpc.newBlockingStub(channel);

            stub.storeConsensusValue(
                    ConsensusValue.newBuilder().setValue(consensusValue).build());

            channel.shutdown();
            channel.awaitTermination(5, TimeUnit.SECONDS);

        }
        System.out.println("=> La valeur de consensus a été envoyée à tous les serveurs.");
    }
}
