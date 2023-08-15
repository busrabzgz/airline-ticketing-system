package com.example.airlineticketingsystem.mapper;


import com.example.airlineticketingsystem.dto.response.TransactionDto;
import com.example.airlineticketingsystem.entitiy.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionDtoMapper {
    private final ModelMapper modelMapper;

    public TransactionDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionDto convert(Transaction from) {
        return modelMapper.map(from, TransactionDto.class);
    }

    public List<TransactionDto> convert(List<Transaction> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

