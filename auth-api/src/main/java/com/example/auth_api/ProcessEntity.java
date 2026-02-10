package com.example.auth_api;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "processing_log")
@Entity
@Data
public class ProcessEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    UUID id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "input_text")
    String inputText;

    @Column(name = "output_text")
    String outputText;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    public ProcessEntity() { }

    public ProcessEntity(UUID userId, String inputText, String outputText, LocalDateTime createdAt) {
        this.userId = userId;
        this.inputText = inputText;
        this.outputText = outputText;
        this.createdAt = createdAt;
    }
}
