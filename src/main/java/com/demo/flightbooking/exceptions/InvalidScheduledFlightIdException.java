package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidScheduledFlightIdException extends HttpRuntimeException {
    public InvalidScheduledFlightIdException() {
        super("The provided scheduled flight id is invalid");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
