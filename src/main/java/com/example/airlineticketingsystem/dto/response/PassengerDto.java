package com.example.airlineticketingsystem.dto.response;

import java.util.List;

public record PassengerDto(
        String id,
        String firstName,
        String lastName,
        String address,
        Integer age,
        String nationality,
        List<PassengerContactDetailDto> contactDetails,
        List<PassengerTransactionDto> transactions
) {
}
