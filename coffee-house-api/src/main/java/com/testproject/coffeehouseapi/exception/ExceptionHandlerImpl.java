package com.testproject.coffeehouseapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler
    public ResponseEntity<?> handleRequestException (RequestException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                e.getStatus(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleRequestStatusException(RequestStatusException e) {
        return new ResponseEntity<>(e.getStatus());
    }
}
