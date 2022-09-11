package com.bridgelabz.note.util;

import lombok.Data;

@Data
public class Response {
    private String message;
    private int errorCode;
    private Object token;

    public Response() {

    }
}
