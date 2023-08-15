package com.example.airlineticketingsystem.mapper;

import com.example.airlineticketingsystem.dto.response.PassengerDto;
import com.example.airlineticketingsystem.entitiy.Passenger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassengerDtoMapper {
    private final ModelMapper modelMapper;

    public PassengerDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PassengerDto convert(Passenger from) {
        return modelMapper.map(from, PassengerDto.class);
    }

    public List<PassengerDto> convert(List<Passenger> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

