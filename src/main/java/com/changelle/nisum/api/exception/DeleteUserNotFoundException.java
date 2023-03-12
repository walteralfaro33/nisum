package com.changelle.nisum.api.exception;

public class DeleteUserNotFoundException extends RuntimeException {
    public DeleteUserNotFoundException(String message) {
        super(message);
    }
}