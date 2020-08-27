package com.example.authservice.service;

import com.example.authservice.entities.User;
import com.example.authservice.payload.request.RegisterRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(RegisterRequest registerRequest);
}
