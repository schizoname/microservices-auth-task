package com.example.auth_api.service;

import com.example.auth_api.AuthDetails;
import com.example.auth_api.AuthEntity;
import com.example.auth_api.repository.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    public AuthDetailsService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthEntity user = authRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new AuthDetails(user.getId(), user.getEmail(), user.getPassword());
    }
}
