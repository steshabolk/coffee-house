package com.project.ch.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ApiError {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "ex.api.userNotFound"),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "ex.api.wrongPassword"),
    COFFEE_HOUSE_NOT_FOUND(HttpStatus.BAD_REQUEST, "ex.api.coffeeHouseNotFound"),
    PRODUCTS_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "ex.api.productsNotAvailable"),
    PRODUCTS_NOT_FOUND(HttpStatus.BAD_REQUEST, "ex.api.productsNotFound"),
    ORDER_NOT_FOUND(HttpStatus.BAD_REQUEST, "ex.api.orderNotFound"),
    ORDER_NOT_ACTIVE(HttpStatus.BAD_REQUEST, "ex.api.orderNotActive"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "ex.api.tokenExpired"),
    MISSING_TOKEN(HttpStatus.BAD_REQUEST, "ex.api.missingToken"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "ex.api.invalidToken");

    private final HttpStatus httpStatus;
    private final String messageProp;

    ApiError(HttpStatus httpStatus, String messageProp) {
        this.httpStatus = httpStatus;
        this.messageProp = messageProp;
    }

    public ApiException toException(Object... args) {
        return new ApiException(messageProp, Arrays.stream(args).map(Object::toString).toArray(String[]::new), name(), httpStatus);
    }
}
