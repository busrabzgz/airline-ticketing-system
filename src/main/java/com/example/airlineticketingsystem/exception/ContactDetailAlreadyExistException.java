package com.example.airlineticketingsystem.exception;

public class ContactDetailAlreadyExistException extends RuntimeException {
    public ContactDetailAlreadyExistException(String message) {
        super(message);
    }
}
