package com.example.airlineticketingsystem.dto.response;

import java.util.List;

public record RouteDto(
        String id,
        String airport,
        String destination,
        String routeCode,
        List<RouteAirFareDto> airFares
) {
    public void setAirFares(List<RouteAirFareDto> convert) {
    }
}
