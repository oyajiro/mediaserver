package com.oyajiro.exception;

import org.springframework.http.HttpStatus;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

/**
 * Created by user on 30.07.16.
 */
//TODO add LOGGER
public class RestControllerException extends WebApplicationException {
    public RestControllerException(String message) {
        super(message, Response.Status.EXPECTATION_FAILED);
    }

    public RestControllerException(String message, Response.Status status) {
        super(message, status);
    }

    public static RestControllerException notFoundException(String id) {
        return new RestControllerException("Entity with id not found: " + id);
    }

    public static RestControllerException notCreatedException(Throwable e) {
        return new RestControllerException("Cannot create file " + e.getMessage(), INTERNAL_SERVER_ERROR);
    }

    public static RestControllerException internalServerErrorException() {
        return new RestControllerException("Internal error", INTERNAL_SERVER_ERROR);
    }
}
