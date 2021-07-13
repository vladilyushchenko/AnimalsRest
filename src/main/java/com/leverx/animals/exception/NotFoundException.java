package com.leverx.animals.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> aClass, long id) {
        super(String.format("There is no object of %s class with id %d", aClass.getName(), id));
    }
}
