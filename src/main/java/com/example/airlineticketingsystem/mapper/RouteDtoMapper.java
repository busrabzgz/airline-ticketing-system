package com.example.airlineticketingsystem.mapper;


import com.example.airlineticketingsystem.dto.response.RouteDto;
import com.example.airlineticketingsystem.entitiy.Route;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouteDtoMapper {
    private final ModelMapper modelMapper;
    private final RouteAirFareDtoMapper routeAirFareDtoMapper;

    public RouteDtoMapper(ModelMapper modelMapper, RouteAirFareDtoMapper routeAirFareDtoMapper) {
        this.modelMapper = modelMapper;
        this.routeAirFareDtoMapper = routeAirFareDtoMapper;
    }

    public RouteDto convert(Route from) {
        RouteDto dto = modelMapper.map(from, RouteDto.class);
        dto.setAirFares(routeAirFareDtoMapper.convert(from.getAirFares()));
        return dto;
    }

    public List<RouteDto> convert(List<Route> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

