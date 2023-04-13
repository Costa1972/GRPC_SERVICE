package ru.costa;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.costa.grpc.GRPCServiceGrpc.*;
import ru.costa.grpc.GRPCServiceGrpc;
import ru.costa.grpc.GRPCServiceOuterClass.*;

import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private Logger logger = Logger.getLogger(Client.class.getName());
    private static Scanner scanner = new Scanner(System.in);
    private String name;

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        System.out.println("Enter your name, please: ");
        client.name = scanner.next();
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:8090")
                .usePlaintext()
                .build();
        HelloRequest request = HelloRequest.newBuilder()
                .setName(client.name)
                .build();
        client.logger.log(Level.INFO, "Well done!!!");
        while (true) {
            GRPCServiceBlockingStub stub = GRPCServiceGrpc
                    .newBlockingStub(channel);
            Optional<Iterator<HelloResponse>> responseIterator = Optional.of(stub.greeting(request));
            responseIterator.ifPresent(r -> System.out.println(r.next()));
            Thread.sleep(1000);
        }
    }
}
