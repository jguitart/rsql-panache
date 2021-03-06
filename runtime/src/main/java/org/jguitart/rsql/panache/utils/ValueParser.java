package org.jguitart.rsql.panache.utils;

import java.time.Instant;
import java.util.Date;

public class ValueParser {

    private ValueParser() {}

    public static Object parseValue(String value, Class type) {
        Object result = null;

        if(type.isEnum()) {
            result = Enum.valueOf(type, value);
            return result;
        }

        if(type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
            result = Long.parseLong(value);
            return result;
        }

        if(type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
            result = Integer.parseInt(value);
        }

        if(type.isAssignableFrom(double.class) || type.isAssignableFrom(Double.class)) {
            result = Double.parseDouble(value);
        }

        if(type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
            result = Float.parseFloat(value);
        }

        if(type.isAssignableFrom(boolean.class) || type.isAssignableFrom(Boolean.class)) {
            result = Boolean.parseBoolean(value);
        }

        if(type.isAssignableFrom(String.class)) {
            result = value;
        }

        // Date and time classes will work from ecpochMillis
        if(type.isAssignableFrom(Instant.class)) {
            result = Instant.ofEpochMilli(Long.parseLong(value));
        }

        if(type.isAssignableFrom(Date.class )) {
            result = Date.from(Instant.ofEpochMilli(Long.parseLong(value)));
        }

        return result;
    }

}
