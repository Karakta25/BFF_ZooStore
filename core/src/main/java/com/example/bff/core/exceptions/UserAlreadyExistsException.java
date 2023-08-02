package com.example.bff.core.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "UserAlreadyExists.";
    public UserAlreadyExistsException() {
        super(MESSAGE);
    }
}
