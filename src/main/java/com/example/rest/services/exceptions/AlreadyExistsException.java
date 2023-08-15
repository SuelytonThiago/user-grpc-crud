package com.example.rest.services.exceptions;

import io.grpc.Status;

public class AlreadyExistsException extends BaseBusinessException{

    private static final String ERROR_MESSAGE = "User email %s already exists";
    private final String email;

    public AlreadyExistsException(String email) {
        super(String.format(ERROR_MESSAGE,email));
        this.email = email;
    }

    @Override
    public Status getStatusCode() {
        return Status.ALREADY_EXISTS;
    }

    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE,email);
    }
}
