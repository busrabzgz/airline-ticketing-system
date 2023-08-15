package com.example.airlineticketingsystem.dto.response;


import java.time.LocalDate;

public record AirCraftDto(
        String id,
        String number,
        Integer capacity,
        String manufacturer,
        LocalDate manufactureDate,
        String flightScheduleId
) {
}