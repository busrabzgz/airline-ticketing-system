package com.example.airlineticketingsystem.repository;


import com.example.airlineticketingsystem.entitiy.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StateRepository extends JpaRepository<State, String> {
}