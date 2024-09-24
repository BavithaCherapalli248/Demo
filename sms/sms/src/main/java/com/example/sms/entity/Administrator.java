package com.example.sms.entity;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "administrators")
public class Administrator {

    @Id
    @Column(length = 10)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Constructors, getters, and setters

    public Administrator() {
    	this.id = generateUniqueId();
    }

    public Administrator(String email, String username, String password) {
    	this();
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String long1) {
        this.id = long1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static String generateUniqueId() {
        // Generate UUID
        UUID uuid = UUID.randomUUID();
        
        // Convert UUID to string and remove hyphens
        String uuidString = uuid.toString().replace("-", "");
        
        // Take the first 10 characters
        return uuidString.substring(0, 10);
    }

    // Optional: toString(), hashCode(), and equals() methods
}

