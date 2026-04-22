package com.demo.flightbooking.controllers;

import com.demo.flightbooking.dtos.ExceptionMessageDto;
import com.demo.flightbooking.exceptions.HttpRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler( HttpRuntimeException.class )
    public ResponseEntity<ExceptionMessageDto> handleArgumentException(HttpRuntimeException ex, ServletWebRequest request) {
        return ExceptionMessageDto.response(ex, request, ex.getHttpStatus());
    }
}

