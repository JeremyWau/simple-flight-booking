package com.demo.flightbooking.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
@ToString
public class ExceptionMessageDto {
    private Instant timestamp;
    private String method;
    private String path;
    private String error;
    private String message;
    private List<String> trace;

    public ExceptionMessageDto() {}

    public ExceptionMessageDto(RuntimeException exception, ServletWebRequest request ) {
        timestamp = Instant.now();

        path = request.getRequest().getRequestURI();
        method = request.getHttpMethod().name();

        error = exception.getClass().getSimpleName();
        message = exception.getMessage();
        trace = Stream.of(exception.getStackTrace()).map(elem -> {
            String methodName = ("method " + elem.getMethodName() + "()");
            methodName = methodName.substring(0, Math.min(methodName.length(), 30));
            return String.format("%-30s: line %04d -> %s"
                    , methodName
                    , elem.getLineNumber()
                    , elem.getFileName());
        }).collect(Collectors.toList()).subList(0, 5);
    }

    public static ResponseEntity<ExceptionMessageDto> response(RuntimeException exception, ServletWebRequest request, HttpStatus status) {
        return new ResponseEntity<>(new ExceptionMessageDto(exception, request), status);
    }
}
