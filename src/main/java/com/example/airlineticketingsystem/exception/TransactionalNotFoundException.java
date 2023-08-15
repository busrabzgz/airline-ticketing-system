package com.example.airlineticketingsystem.exception;

public class TransactionalNotFoundException extends RuntimeException {
    public TransactionalNotFoundException(String message) {
        super(message);
    }
}
