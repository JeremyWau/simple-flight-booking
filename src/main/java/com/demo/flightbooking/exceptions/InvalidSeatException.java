package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidSeatException extends HttpRuntimeException {
    public InvalidSeatException() {
        super("You must provide an amount of seat strictly greater than 0");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
