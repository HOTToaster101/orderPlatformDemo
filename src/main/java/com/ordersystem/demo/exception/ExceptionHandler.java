package com.ordersystem.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error illegalArgumentExceptionResponse(IllegalArgumentException e){
        String response = "Illegal Argument";
        Error error = new Error();
        error.setMessage(e.getMessage());
        return error;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {IndexOutOfBoundsException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error indexOutOfBoundsExceptionResponse(IndexOutOfBoundsException e){
        String response = "Index Out Of Bounds";
        Error error = new Error();
        error.setMessage(e.getMessage());
        return error;
    }
}
