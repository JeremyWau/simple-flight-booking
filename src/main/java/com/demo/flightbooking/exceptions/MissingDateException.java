package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class MissingDateException extends HttpRuntimeException {
    public MissingDateException() {
        super("Date is missing");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
