package com.example.airlineticketingsystem.dto.response;

import java.time.LocalDate;

public record TransactionDto(
        String id,
        LocalDate bookingDate,
        LocalDate departureDate,
        String passengerId,
        String flightScheduleId,
        String type
) {
}
