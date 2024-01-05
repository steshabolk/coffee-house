package com.project.ch.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponseStatus extends RuntimeException {

    private HttpStatus status;

    public ApiResponseStatus(HttpStatus status) {
        this.status = status;
    }
}
