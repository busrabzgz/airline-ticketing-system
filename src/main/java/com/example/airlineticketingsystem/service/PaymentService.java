package com.example.airlineticketingsystem.service;

import com.example.airlineticketingsystem.entitiy.Passenger;
import com.example.airlineticketingsystem.entitiy.Payment;
import com.example.airlineticketingsystem.entitiy.Transaction;
import com.example.airlineticketingsystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void makePayment(Passenger passenger, Transaction transaction, BigDecimal amount, String maskedCardNumber) {
        Payment payment = new Payment();
        payment.setTransaction(transaction);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setMaskedCardNumber(maskedCardNumber);


        paymentRepository.save(payment);
    }
}
