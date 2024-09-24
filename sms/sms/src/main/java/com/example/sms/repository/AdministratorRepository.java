package com.example.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sms.entity.Administrator;


public interface AdministratorRepository extends JpaRepository<Administrator, String> {
    Administrator findByUsername(String username);
    Administrator findByEmail(String email);
}

