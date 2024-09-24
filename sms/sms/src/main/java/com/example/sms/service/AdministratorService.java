package com.example.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sms.entity.Administrator;
import com.example.sms.repository.AdministratorRepository;

import java.util.UUID;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    // Method to register a new administrator
    public Administrator registerAdministrator(Administrator administrator) {
        

        // Save the administrator to the database
        return administratorRepository.save(administrator);
    }

    public boolean authenticate(String username, String password) {
        Administrator administrator = administratorRepository.findByUsername(username);
        if (administrator != null) {
            return administrator.getPassword().equals(password);
        }
        return false;
    }


    public Administrator findByUsername(String username) {
        return administratorRepository.findByUsername(username);
    }

    public Administrator findByEmail(String email) {
        return administratorRepository.findByEmail(email);
    }
}

