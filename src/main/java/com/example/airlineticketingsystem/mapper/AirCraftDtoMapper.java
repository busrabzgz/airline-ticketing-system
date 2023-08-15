package com.example.airlineticketingsystem.mapper;

import com.example.airlineticketingsystem.dto.response.AirCraftDto;
import com.example.airlineticketingsystem.entitiy.AirCraft;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


public class AirCraftDtoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public AirCraftDto convert(AirCraft from) {
        return modelMapper.map(from, AirCraftDto.class);
    }

    public List<AirCraftDto> convert(List<AirCraft> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
