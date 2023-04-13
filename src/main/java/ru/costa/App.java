package ru.costa;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Costa Vashchuk
 */
public class App {
    static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Server server = ServerBuilder
                .forPort(8090)
                .addService(new GRPC())
                .build();
        try {
        server.start();
        logger.log(Level.INFO, "Server started...");
        server.awaitTermination();
        }catch (IOException | InterruptedException e) {
            logger.log(Level.WARNING, "Unable to start server!");
        }
    }
}
