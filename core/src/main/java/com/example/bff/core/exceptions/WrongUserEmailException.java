package com.example.bff.core.exceptions;

public class WrongUserEmailException extends RuntimeException{

    private static final String MESSAGE = "Wrong user email";
    public WrongUserEmailException() {
        super(MESSAGE);
    }
}
