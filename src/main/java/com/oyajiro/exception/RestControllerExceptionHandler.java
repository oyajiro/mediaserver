package com.oyajiro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by user on 30.07.16.
 */
@ControllerAdvice
public class RestControllerExceptionHandler {
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(RestControllerException.class)
    public String handleRestControllerException(RestControllerException e) {
        //TODO log exception
        return e.getMessage();
    }
}
