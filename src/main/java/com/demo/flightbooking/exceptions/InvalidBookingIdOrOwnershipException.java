package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidBookingIdOrOwnershipException extends HttpRuntimeException {
    public InvalidBookingIdOrOwnershipException() {
        super("The provided booking id either does not exist or does not belong to you");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
