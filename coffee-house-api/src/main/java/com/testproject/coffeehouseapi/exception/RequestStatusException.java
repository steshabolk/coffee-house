package com.testproject.coffeehouseapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RequestStatusException extends RuntimeException {

    private final HttpStatus status;

    public RequestStatusException(HttpStatus status) {
        this.status = status;
    }
}
