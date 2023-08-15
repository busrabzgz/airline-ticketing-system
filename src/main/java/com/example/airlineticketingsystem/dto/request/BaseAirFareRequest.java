package com.example.airlineticketingsystem.dto.request;

import com.example.airlineticketingsystem.helper.message.ValidationMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAirFareRequest {
    @NotEmpty(message = ValidationMessage.AirFare.FARE_NOT_EMPTY)
    @NotNull(message = ValidationMessage.AirFare.FARE_NOT_NULL)
    private Double fare;

    @NotEmpty(message = ValidationMessage.AirFare.ROUTE_ID_NOT_EMPTY)
    @NotNull(message = ValidationMessage.AirFare.ROUTE_ID_NOT_NULL)
    private String routeId;
}
