package com.example.airlineticketingsystem.repository;

import com.example.airlineticketingsystem.entitiy.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    boolean existsByNameIgnoreCase(String name);
}