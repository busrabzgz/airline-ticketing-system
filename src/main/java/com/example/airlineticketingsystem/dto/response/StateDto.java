package com.example.airlineticketingsystem.dto.response;


public record StateDto(
        String id,
        String name,
        StateCountryDto country
) {
    public void setCountry(StateCountryDto convert) {
    }
}
