package com.changelle.nisum.api.exception.api;

import lombok.Builder;
import lombok.Data;

@Builder
public class ErrorResponse {
    public String getMessage() {
        return message;
    }

    private String message;

}
