package com.example.airlineticketingsystem.exception;

public class AirCraftAlreadyExistException extends RuntimeException {
    public AirCraftAlreadyExistException(String message) {
        super(message);
    }
}
