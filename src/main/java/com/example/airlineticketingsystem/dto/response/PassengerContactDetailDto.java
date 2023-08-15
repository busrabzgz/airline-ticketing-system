package com.example.airlineticketingsystem.dto.response;

public record PassengerContactDetailDto(
        String id,
        String email,
        String phone,
        String street,
        String stateId
) {
}
