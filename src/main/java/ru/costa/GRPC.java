package ru.costa;

import io.grpc.stub.StreamObserver;
import ru.costa.grpc.GRPCServiceGrpc;
import ru.costa.grpc.GRPCServiceOuterClass;

public class GRPC extends GRPCServiceGrpc.GRPCServiceImplBase {
    @Override
    public void greeting(GRPCServiceOuterClass.HelloRequest request,
                         StreamObserver<GRPCServiceOuterClass.HelloResponse> streamObserver) {
        System.out.println(request);
        GRPCServiceOuterClass.HelloResponse response = GRPCServiceOuterClass.HelloResponse.newBuilder()
                .setGreeting("Hello from server, " + request.getName())
                .build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }
}
