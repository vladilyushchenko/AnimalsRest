package com.leverx.animals.exception;

public class UpdatingIdException extends RuntimeException {

    public static final String MESSAGE_FOR_CLASS = "Updating id while updating entity" +
            " of class %s forbidden";

    public UpdatingIdException(Class<?> aClass) {
        super(String.format(MESSAGE_FOR_CLASS, aClass.getName()));
    }

}
