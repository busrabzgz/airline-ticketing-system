package com.example.airlineticketingsystem.exception;

public class PassengerAlreadyExistException extends RuntimeException {
    public PassengerAlreadyExistException(String message) {
        super(message);
    }
}
