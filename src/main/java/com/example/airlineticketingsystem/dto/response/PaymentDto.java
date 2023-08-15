package com.example.airlineticketingsystem.dto.response;

import java.math.BigDecimal;

public record PaymentDto(
         String passengerId,
         String transactionId,
         BigDecimal amount
) {
}
