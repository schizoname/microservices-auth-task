package com.example.auth_api.service;

import com.example.auth_api.*;
import com.example.auth_api.repository.AuthRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtCore jwtCore;

    public AuthService(AuthRepository repository,
                       AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder,
                       JwtCore jwtCore) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtCore = jwtCore;
    }

    public TokenResponse login(LogRequest logRequest) {

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logRequest.getEmail(),
                            logRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String jwt = jwtCore.generateToken(authentication);

        return new TokenResponse(jwt);
    }

    public RegisterResponse register(LogRequest logRequest) {

        if (repository.existsByEmail(logRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (logRequest.getPassword() == null || logRequest.getPassword().isBlank()) {
            throw new RuntimeException("Password is required");
        }


        AuthEntity user = new AuthEntity();
        user.setEmail(logRequest.getEmail());
        user.setPassword(passwordEncoder.encode(logRequest.getPassword()));

        var saved = repository.save(user);

        return new RegisterResponse(saved.getId(), saved.getEmail());
    }
}
