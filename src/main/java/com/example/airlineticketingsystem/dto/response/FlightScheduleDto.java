package com.example.airlineticketingsystem.dto.response;


import java.time.LocalDate;
import java.util.List;

public record FlightScheduleDto(
        String id,
        LocalDate flightDate,
        LocalDate departure,
        LocalDate arrival,
        List<FlightScheduleAirCraftDto> airCrafts
) {
}
