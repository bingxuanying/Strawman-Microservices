package com.example.authservice.service;

import com.example.authservice.api.dto.UserDto;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUsername(),
                userDto.getCompany(),
                (ArrayList<Integer>) userDto.getProducts(),
                userDto.getPassword());

        return userRepository.save(user);
    }
}
