package com.example.bff.core.exceptions;

public class InsufficientAvailabilityException extends RuntimeException {
    private static final String MESSAGE = "Insufficient availability";
    public InsufficientAvailabilityException() {
        super(MESSAGE);
    }
}
