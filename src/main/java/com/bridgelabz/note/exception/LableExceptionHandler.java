package com.bridgelabz.note.exception;

import com.bridgelabz.note.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class LableExceptionHandler {
    @ExceptionHandler(LableNotFoundException.class)
    public ResponseEntity<Response> handleHiringException(LableNotFoundException he) {
        Response response = new Response();
        response.setErrorCode(400);
        response.setMessage("Admin not found");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
