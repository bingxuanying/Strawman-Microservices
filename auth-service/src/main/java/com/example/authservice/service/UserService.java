package com.example.authservice.service;

import com.example.authservice.api.dto.UserDto;

import com.example.authservice.model.User;

public interface UserService {
    User save(UserDto userDto);
}
