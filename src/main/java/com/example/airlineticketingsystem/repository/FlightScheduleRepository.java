package com.example.airlineticketingsystem.repository;

import com.example.airlineticketingsystem.entitiy.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, String> {
}