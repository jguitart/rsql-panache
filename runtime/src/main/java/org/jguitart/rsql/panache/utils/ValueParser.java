package org.jguitart.rsql.panache.utils;

public class ValueParser {

    public static Object parseValue(String value, Class type) {
        Object result = null;

        if(type.isEnum()) {
            result = value;
            return result;
        }

        if(type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
            result = Long.parseLong(value);
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



        return result;
    }

}
