package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class BookingExceedsCapacityException extends HttpRuntimeException {
    public BookingExceedsCapacityException(Integer max) {
        super("Your booking exceeds the maximum remaining capacity of " + max);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
