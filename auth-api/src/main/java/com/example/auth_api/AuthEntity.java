package com.example.auth_api;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "users")
@Entity
@Data
public class AuthEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public AuthEntity() { }

    public AuthEntity(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
