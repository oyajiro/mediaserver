package com.oyajiro.utils;

import com.oyajiro.exception.RestControllerException;

/**
 * Created by user on 30.07.16.
 */
public final class EntityUtils {
    private EntityUtils() {
        //Utils class cannot be created
    }

    public static <T> T getOrThrowNotFound(T entity, String id) {
        if (entity != null) {
            return entity;
        }
        throw RestControllerException.notFoundException(id);
    }
}
