package com.example.airlineticketingsystem.mapper;


import com.example.airlineticketingsystem.dto.response.StateDto;
import com.example.airlineticketingsystem.entitiy.State;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateDtoMapper {
    private final ModelMapper modelMapper;
    private final StateCountryDtoMapper stateCountryDtoMapper;

    public StateDtoMapper(ModelMapper modelMapper, StateCountryDtoMapper stateCountryDtoMapper) {
        this.modelMapper = modelMapper;
        this.stateCountryDtoMapper = stateCountryDtoMapper;
    }

    public StateDto convert(State from) {
        StateDto dto = modelMapper.map(from, StateDto.class);
        dto.setCountry(stateCountryDtoMapper.convert(from.getCountry()));
        return dto;
    }

    public List<StateDto> convert(List<State> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

