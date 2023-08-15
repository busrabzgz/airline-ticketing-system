package com.example.airlineticketingsystem.exception;

public class FlightScheduleAlreadyExistException extends RuntimeException {
    public FlightScheduleAlreadyExistException(String message) {
        super(message);
    }
}
