package com.example.bff.core.exceptions;

public class NoSuchItemException extends RuntimeException{
    private static final String MESSAGE = "This item does not exist!";
    public NoSuchItemException() {
        super(MESSAGE);
    }
}
