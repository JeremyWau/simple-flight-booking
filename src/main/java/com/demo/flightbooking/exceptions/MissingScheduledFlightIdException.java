package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class MissingScheduledFlightIdException extends HttpRuntimeException {
    public MissingScheduledFlightIdException() {
        super("You must provide the scheduled flight id");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
