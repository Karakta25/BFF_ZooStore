package com.example.bff.core.exceptions;

public class WrongUserPasswordException extends RuntimeException {
    private static final String MESSAGE = "Wrong user password.";
    public WrongUserPasswordException() {
        super(MESSAGE);
    }
}
