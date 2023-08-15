package com.example.airlineticketingsystem.dto.response;

public record AirFareDto(
        String id,
        Double fare,
        String routeId
) {
}
