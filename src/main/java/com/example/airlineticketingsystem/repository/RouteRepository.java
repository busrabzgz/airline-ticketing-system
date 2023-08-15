package com.example.airlineticketingsystem.repository;

import com.example.airlineticketingsystem.entitiy.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
}