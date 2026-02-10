package com.example.auth_api.repository;

import com.example.auth_api.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<AuthEntity, UUID> {

    Optional<AuthEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
