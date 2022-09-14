package com.bridgelabz.note.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class LableNotFoundException extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public LableNotFoundException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
