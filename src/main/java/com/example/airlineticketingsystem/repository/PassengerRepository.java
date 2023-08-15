package com.example.airlineticketingsystem.repository;

import com.example.airlineticketingsystem.entitiy.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {
    boolean existsByFirstNameIgnoreCase(String firstName);
    boolean existsByLastNameIgnoreCase(String lastName);
}