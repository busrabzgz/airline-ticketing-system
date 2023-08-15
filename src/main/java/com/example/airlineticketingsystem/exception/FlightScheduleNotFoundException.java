package com.example.airlineticketingsystem.exception;

public class FlightScheduleNotFoundException extends RuntimeException {
    public FlightScheduleNotFoundException(String message) {
        super(message);
    }
}
