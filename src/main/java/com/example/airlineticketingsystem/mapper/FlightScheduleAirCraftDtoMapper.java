package com.example.airlineticketingsystem.mapper;


import com.example.airlineticketingsystem.dto.response.FlightScheduleAirCraftDto;
import com.example.airlineticketingsystem.entitiy.AirCraft;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightScheduleAirCraftDtoMapper {
    private final ModelMapper modelMapper;

    public FlightScheduleAirCraftDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FlightScheduleAirCraftDto convert(AirCraft from) {
        return modelMapper.map(from, FlightScheduleAirCraftDto.class);
    }

    public List<FlightScheduleAirCraftDto> convert(List<AirCraft> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

