package com.example.bff.core.exceptions;

public class NoSuchTagException extends RuntimeException {
    private static final String MESSAGE = "This tag does not exist!";
    public NoSuchTagException() {
        super(MESSAGE);
    }
}
