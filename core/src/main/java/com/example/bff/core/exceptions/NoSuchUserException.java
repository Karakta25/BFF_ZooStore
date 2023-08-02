package com.example.bff.core.exceptions;

public class NoSuchUserException extends RuntimeException {

    private static final String MESSAGE = "User does not exist!";
    public NoSuchUserException() {
        super(MESSAGE);
    }
}
