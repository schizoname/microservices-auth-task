package com.example.auth_api.controller;

import com.example.auth_api.LogRequest;
import com.example.auth_api.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogRequest logRequest) {
        log.info("called login: email = {}", logRequest.getEmail());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authService.login(logRequest));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LogRequest logRequest) {
        log.info("called register: email = {}", logRequest.getEmail());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(logRequest));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
