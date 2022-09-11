package com.bridgelabz.note.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NoteNotFoundException extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public NoteNotFoundException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
