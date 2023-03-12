package com.changelle.nisum.api.exception;

public class MailConflictException extends RuntimeException {

    public MailConflictException(String message) {
        super(message);
    }
}