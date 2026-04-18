package rt4.example.server;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import rt4.example.MessageCalculatorProto.*; //MessageRequest, MessageResponse, **Builder
import rt4.example.MessageCalculatorGrpc.*;
//import javax.annotation.processing.Generated; //needed to avoid the error of javax.annotation.generated


import java.io.IOException;

public class MessageServer {

    public static void main(String[] args) throws IOException, InterruptedException {
    //créer une instance du Serveur et configurer cette  dernière via l'objet serverBuilder (classe utilitaire)
        Server server = ServerBuilder.forPort(50051)
                .addService(new CalculatorServiceImpl())
                .build();


        System.out.println("Server started on port 50051");
        server.start();
        server.awaitTermination();
    }

    static class CalculatorServiceImpl extends MessageCalculatorImplBase {
        @Override
        public void processMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
            String text = request.getText();
            String processedText = "Processed: " + text.toUpperCase();
            System.out.println("Reçu:"+text+"----> "+processedText);

            // Création d'une réponse
            MessageResponse response = MessageResponse.newBuilder()
                    .setProcessedText(processedText)
                    .build();

            // Envoi de la réponse au client sous forme de flux
            responseObserver.onNext(response);
            // Indique que toutes les données ont été envoyées
            responseObserver.onCompleted();//

            //onError(Throwable) : Envoie une erreur au client pour indiquer qu'une exception ou une erreur s'est produite.
        }
    }
}
