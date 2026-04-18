package rt4.example.client;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import rt4.example.MessageCalculatorGrpc;
import rt4.example.MessageCalculatorProto.MessageResponse;
import rt4.example.MessageCalculatorProto.MessageRequest;
//import javax.annotation.processing.Generated;


import java.util.Scanner;

public class MessageClient {

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true"); // forcer

        // Initialisation du canal gRPC
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50051)
                .usePlaintext() // => pas de chiffrement
                .build();

        // Création du stub
        MessageCalculatorGrpc.MessageCalculatorBlockingStub stub = MessageCalculatorGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);

        // Boucle pour permettre plusieurs entrées utilisateur
        while (true) {
            System.out.println("Entrez un texte à traiter (ou tapez 'exit' pour quitter) :");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break; // Quitter la boucle si l'utilisateur entre 'exit'
            }

            // Construire la requête
            MessageRequest request = MessageRequest.newBuilder()
                    .setText(input)
                    .build();

            // Envoyer la requête et recevoir la réponse
            MessageResponse response = stub.processMessage(request);
            System.out.println("Réponse du serveur : " + response.getProcessedText());
        }

        // Fermeture du canal
        channel.shutdown();
    }
}
