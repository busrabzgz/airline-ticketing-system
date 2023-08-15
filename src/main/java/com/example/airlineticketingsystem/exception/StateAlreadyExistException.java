package com.example.airlineticketingsystem.exception;

public class StateAlreadyExistException extends RuntimeException {
    public StateAlreadyExistException(String message) {
        super(message);
    }
}
