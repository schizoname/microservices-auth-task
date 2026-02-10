package com.example.auth_api.repository;

import com.example.auth_api.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcessRepository extends JpaRepository<ProcessEntity, UUID> {

}
