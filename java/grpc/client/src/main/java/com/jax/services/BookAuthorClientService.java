package com.jax.services;

import com.google.protobuf.Descriptors;
import com.jax.grpc.proto.Author;
import com.jax.grpc.proto.BookAuthorServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookAuthorClientService {

    @GrpcClient("grpc-jax-service")
    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub synchronousClient;

    public Map<Descriptors.FieldDescriptor, Object> getAuthor(int authorId) {
        Author authorRequest = Author.newBuilder().setAuthorId(authorId).build();
        var authorResponse = synchronousClient.getAuthor(authorRequest);
        return authorResponse.getAllFields();
    }
}
