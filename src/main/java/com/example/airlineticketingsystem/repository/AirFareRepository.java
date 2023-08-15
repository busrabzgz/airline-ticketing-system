package com.example.airlineticketingsystem.repository;

import com.example.airlineticketingsystem.entitiy.AirFare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirFareRepository extends JpaRepository<AirFare, String> {
}