package com.example.airlineticketingsystem.repository;

import com.example.airlineticketingsystem.entitiy.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}