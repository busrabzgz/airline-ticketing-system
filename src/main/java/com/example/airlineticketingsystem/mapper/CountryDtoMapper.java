package com.example.airlineticketingsystem.mapper;


import com.example.airlineticketingsystem.dto.response.CountryDto;
import com.example.airlineticketingsystem.entitiy.Country;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryDtoMapper {
    private final ModelMapper modelMapper;

    public CountryDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CountryDto convert(Country from) {
        return modelMapper.map(from, CountryDto.class);
    }

    public List<CountryDto> convert(List<Country> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

