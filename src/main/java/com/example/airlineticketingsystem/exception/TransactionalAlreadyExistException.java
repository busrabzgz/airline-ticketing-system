package com.example.airlineticketingsystem.exception;

public class TransactionalAlreadyExistException extends RuntimeException {
    public TransactionalAlreadyExistException(String message) {
        super(message);
    }
}
