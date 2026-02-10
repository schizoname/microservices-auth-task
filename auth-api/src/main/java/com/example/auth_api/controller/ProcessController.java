package com.example.auth_api.controller;

import com.example.auth_api.AuthDetails;
import com.example.auth_api.ProcessRequest;
import com.example.auth_api.service.ProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProcessController {
    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @PostMapping("/process")
    public ResponseEntity<?> process(@RequestBody ProcessRequest processRequest, Authentication authentication) {
        String email = authentication.getName();
        AuthDetails details = (AuthDetails) authentication.getPrincipal();
        UUID userId = details.getId();

        try {
            return ResponseEntity.ok(processService.process(processRequest.getText(), userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
