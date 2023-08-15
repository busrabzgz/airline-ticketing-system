package com.example.airlineticketingsystem.mapper;


import com.example.airlineticketingsystem.dto.response.RouteAirFareDto;
import com.example.airlineticketingsystem.entitiy.AirFare;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouteAirFareDtoMapper {
    private final ModelMapper modelMapper;

    public RouteAirFareDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RouteAirFareDto convert(AirFare from) {
        return modelMapper.map(from, RouteAirFareDto.class);
    }

    public List<RouteAirFareDto> convert(List<AirFare> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

