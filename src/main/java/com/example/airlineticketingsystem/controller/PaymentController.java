package com.example.airlineticketingsystem.controller;


import com.example.airlineticketingsystem.dto.request.PaymentRequest;
import com.example.airlineticketingsystem.entitiy.Passenger;
import com.example.airlineticketingsystem.entitiy.Transaction;
import com.example.airlineticketingsystem.service.PassengerService;
import com.example.airlineticketingsystem.service.PaymentService;
import com.example.airlineticketingsystem.service.TransactionService;
import com.example.airlineticketingsystem.utilities.CardMaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController

@RequestMapping("/api/v1/payment")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;
    private final PassengerService passengerService;
    private final TransactionService transactionService;

    @Autowired
    public PaymentController(PaymentService paymentService, PassengerService passengerService, TransactionService transactionService) {
        this.paymentService = paymentService;
        this.passengerService = passengerService;
        this.transactionService = transactionService;
    }

    @PostMapping("/makePayment")
    public String makePayment(@RequestBody PaymentRequest paymentRequest) {
        Optional<Passenger> passenger = passengerService.getPassengerById(paymentRequest.getPassengerId());
        Optional<Transaction> transaction = transactionService.getTransactionById(paymentRequest.getTransactionId());

        if (passenger.isPresent() && transaction.isPresent()) {
            String maskedCardNumber = CardMaskUtil.maskCardNumber(paymentRequest.getCardNumber());
            paymentService.makePayment(passenger.get(), transaction.get(), paymentRequest.getAmount(), maskedCardNumber);

            return "Payment successful!";
        } else {
            return "Invalid passenger or transaction information.";
        }
    }
}
