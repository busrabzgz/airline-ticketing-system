package com.example.airlineticketingsystem.exception;

public class AirFareNotFoundException extends RuntimeException {
    public AirFareNotFoundException(String message) {
        super(message);
    }
}
