package com.example.airlineticketingsystem.mapper;

;
import com.example.airlineticketingsystem.dto.response.AirFareDto;
import com.example.airlineticketingsystem.entitiy.AirFare;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AirFareDtoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public AirFareDto convert(AirFare from) {
        return modelMapper.map(from, AirFareDto.class);
    }

    public List<AirFareDto> convert(List<AirFare> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
