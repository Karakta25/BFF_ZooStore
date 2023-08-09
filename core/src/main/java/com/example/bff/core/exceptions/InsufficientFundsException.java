package com.example.bff.core.exceptions;

public class InsufficientFundsException extends RuntimeException{

    private static final String MESSAGE = "Insufficient funds: The cardholder doesn't have the funds available in their bank account to cover the transaction.";
    public InsufficientFundsException() {
        super(MESSAGE);
    }
}
