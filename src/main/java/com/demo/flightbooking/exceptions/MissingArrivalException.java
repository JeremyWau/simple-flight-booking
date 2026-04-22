package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class MissingArrivalException extends HttpRuntimeException {
    public MissingArrivalException() {
        super("Arrival is missing");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
