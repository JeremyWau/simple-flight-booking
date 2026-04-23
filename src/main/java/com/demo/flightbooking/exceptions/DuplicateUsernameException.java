package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends HttpRuntimeException {
    public DuplicateUsernameException(String message) {
        super("Error, the username is already in use: " + message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
