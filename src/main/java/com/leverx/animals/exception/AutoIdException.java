package com.leverx.animals.exception;

public class AutoIdException extends RuntimeException {

    public static final String MESSAGE_FOR_CLASS = "Do not specify id for object %s when creating object at db";

    public AutoIdException(Class<?> aClass) {
        super(String.format(MESSAGE_FOR_CLASS, aClass.getName()));
    }
}
