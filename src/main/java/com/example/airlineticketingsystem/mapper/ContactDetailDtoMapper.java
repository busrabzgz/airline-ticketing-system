package com.example.airlineticketingsystem.mapper;


import com.example.airlineticketingsystem.dto.response.ContactDetailDto;
import com.example.airlineticketingsystem.entitiy.ContactDetail;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ContactDetailDtoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public ContactDetailDto convert(ContactDetail from) {
        return modelMapper.map(from, ContactDetailDto.class);
    }

    public List<ContactDetailDto> convert(List<ContactDetail> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}

