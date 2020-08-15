package com.example.authservice.service;

import com.example.authservice.payload.request.RegisterRequest;

import com.example.authservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(RegisterRequest registerRequest);
}
