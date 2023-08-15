package com.example.airlineticketingsystem.dto.request;



import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String passengerId;
    private String transactionId;
    private BigDecimal amount;
    private String  cardNumber;
}

