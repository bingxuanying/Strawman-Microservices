package com.example.authservice.service;

import com.example.authservice.payload.request.RegisterReq;

import com.example.authservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(RegisterReq registerReq);
}
