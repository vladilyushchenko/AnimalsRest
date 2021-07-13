package com.leverx.animals.exception;

public class IncorrectRequestIdException extends RuntimeException {

    public IncorrectRequestIdException(String msg) {
        super(msg);
    }
}
