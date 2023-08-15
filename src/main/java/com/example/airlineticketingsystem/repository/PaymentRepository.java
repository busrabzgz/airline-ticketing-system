package com.example.airlineticketingsystem.repository;


import com.example.airlineticketingsystem.entitiy.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}
