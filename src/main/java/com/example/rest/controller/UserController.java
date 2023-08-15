package com.example.rest.controller;

import com.example.*;
import com.example.rest.dto.UserImputDto;
import com.example.rest.services.impl.UserServiceImpl;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class UserController extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void create(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        var userImput = new UserImputDto(request.getName(),request.getEmail(), request.getPass());

        var userOutput = this.userService.create(userImput);
        UserResponse userResponse = UserResponse.newBuilder()
                .setId(userOutput.getId())
                .setName(userImput.getName())
                .setEmail(userOutput.getEmail())
                .setPass(userOutput.getPass())
                .build();
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(RequestId request, StreamObserver<UserResponse> responseObserver) {
        var userOutput = userService.findById(request.getId());
        UserResponse userResponse = UserResponse.newBuilder()
                .setId(userOutput.getId())
                .setName(userOutput.getName())
                .setEmail(userOutput.getEmail())
                .setPass(userOutput.getPass())
                .build();
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(EmptyRequest request, StreamObserver<UsersResponseList> responseObserver) {
        var userOutputList = userService.findAll();
        List<UserResponse> userListOutput = userOutputList.stream()
                .map(userOutput -> UserResponse.newBuilder()
                    .setId(userOutput.getId())
                    .setName(userOutput.getName())
                    .setEmail(userOutput.getEmail())
                    .setPass(userOutput.getPass())
                    .build())
                .collect(Collectors.toList());

        UsersResponseList usersResponse = UsersResponseList
                .newBuilder()
                .addAllUsers(userListOutput)
                .build();
        responseObserver.onNext(usersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(RequestId request, StreamObserver<EmptyResponse> responseObserver) {
        userService.delete(request.getId());
        responseObserver.onNext(EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
