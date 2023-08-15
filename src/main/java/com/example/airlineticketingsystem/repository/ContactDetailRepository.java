package com.example.airlineticketingsystem.repository;

import com.example.airlineticketingsystem.entitiy.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetail, String> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}