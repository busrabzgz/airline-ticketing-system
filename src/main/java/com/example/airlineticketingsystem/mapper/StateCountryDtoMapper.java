package com.example.airlineticketingsystem.mapper;

import com.example.airlineticketingsystem.dto.response.StateCountryDto;
import com.example.airlineticketingsystem.entitiy.Country;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateCountryDtoMapper {
    private final ModelMapper modelMapper;

    public StateCountryDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StateCountryDto convert(Country from) {
        return modelMapper.map(from, StateCountryDto.class);
    }

    public List<StateCountryDto> convert(List<Country> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

