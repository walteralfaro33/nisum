package com.changelle.nisum.api.exception;

public class CreateUserNotFoundException extends RuntimeException {

    public CreateUserNotFoundException(String message) {
        super(message);
    }
}