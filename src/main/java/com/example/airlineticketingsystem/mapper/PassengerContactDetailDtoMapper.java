package com.example.airlineticketingsystem.mapper;

import com.example.airlineticketingsystem.dto.response.PassengerContactDetailDto;
import com.example.airlineticketingsystem.entitiy.ContactDetail;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassengerContactDetailDtoMapper {
    private final ModelMapper modelMapper;

    public PassengerContactDetailDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PassengerContactDetailDto convert(ContactDetail from) {
        return modelMapper.map(from, PassengerContactDetailDto.class);
    }

    public List<PassengerContactDetailDto> convert(List<ContactDetail> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

