package com.example.airlineticketingsystem.repository;


import com.example.airlineticketingsystem.entitiy.AirCraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirCraftRepository extends JpaRepository<AirCraft, String> {
    AirCraft findByNumber(String number);
}