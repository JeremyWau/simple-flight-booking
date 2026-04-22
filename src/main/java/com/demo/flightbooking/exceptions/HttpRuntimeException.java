package com.demo.flightbooking.exceptions;

import org.springframework.http.HttpStatus;

public class HttpRuntimeException extends RuntimeException{
    public HttpRuntimeException() {
        super ();
    }

    public HttpRuntimeException(String message) {
        super (message);
    }

    public HttpRuntimeException(String formatString, Object...parameters) {
        super (String.format(formatString, parameters));
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
