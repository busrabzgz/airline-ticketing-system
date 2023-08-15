package com.example.airlineticketingsystem.dto.response;

import java.time.LocalDate;

public record PassengerTransactionDto(
        String id,
        LocalDate bookingDate,
        LocalDate departureDate,
        String flightScheduleId,
        String type
) {
}
