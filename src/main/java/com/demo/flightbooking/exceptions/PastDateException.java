package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class PastDateException extends HttpRuntimeException {
    public PastDateException() {
        super("Date must be today or in the future");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
