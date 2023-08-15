package com.example.airlineticketingsystem.exception;

public class AirCraftNotFoundException extends RuntimeException {
    public AirCraftNotFoundException(String message) {
        super(message);
    }
}
