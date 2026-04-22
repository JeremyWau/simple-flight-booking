package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class MissingDepartureException extends HttpRuntimeException {
    public MissingDepartureException() {
        super("Departure is missing");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
