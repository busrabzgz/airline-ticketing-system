package com.example.airlineticketingsystem.mapper;

import com.example.airlineticketingsystem.dto.response.FlightScheduleDto;
import com.example.airlineticketingsystem.entitiy.FlightSchedule;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightScheduleDtoMapper {
    private final ModelMapper modelMapper;

    public FlightScheduleDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FlightScheduleDto convert(FlightSchedule from) {
        return modelMapper.map(from, FlightScheduleDto.class);
    }

    public List<FlightScheduleDto> convert(List<FlightSchedule> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

