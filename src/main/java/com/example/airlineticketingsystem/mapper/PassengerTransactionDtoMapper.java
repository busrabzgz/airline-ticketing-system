package com.example.airlineticketingsystem.mapper;


import com.example.airlineticketingsystem.dto.response.PassengerTransactionDto;
import com.example.airlineticketingsystem.entitiy.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassengerTransactionDtoMapper {
    private final ModelMapper modelMapper;

    public PassengerTransactionDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PassengerTransactionDto convert(Transaction from) {
        return modelMapper.map(from, PassengerTransactionDto.class);
    }

    public List<PassengerTransactionDto> convert(List<Transaction> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
