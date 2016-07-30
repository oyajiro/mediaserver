package com.oyajiro.utils;

/**
 * Created by user on 30.07.16.
 */
public final class StringUtils {
    private StringUtils() {
        //Utils class cannot be created
    }

    public static boolean isBlank(String s) {
        return s != null && !s.isEmpty();
    }
}
