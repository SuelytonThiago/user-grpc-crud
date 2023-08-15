package com.example.rest.services.exceptions;

import io.grpc.Status;

public class ObjectNotFoundException extends BaseBusinessException{

    private static final String ERROR_MESSAGE = "User id: %s not found";
    private final String id;

    public ObjectNotFoundException(String id) {
        super(String.format(ERROR_MESSAGE,id));
        this.id = id;
    }

    @Override
    public Status getStatusCode() {
        return Status.NOT_FOUND;
    }

    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE,id);
    }
}
