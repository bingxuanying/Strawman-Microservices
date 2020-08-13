package com.example.authservice.service;

import com.example.authservice.api.dto.UserDto;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

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

        if (userRepository.existsById((long) 1)) {
            var find = userRepository.getOne((long) 1);
            Set<Integer> set = new LinkedHashSet<>(find.getProducts());

            List<Integer> newList = new ArrayList<>(Arrays.asList(1,2,3));
            set.addAll(newList);
            List<Integer> combinedList = new ArrayList<>(set);

            System.out.println("old: " + find.getProducts());
            System.out.println("new: " + combinedList);
        }

        return userRepository.save(user);
    }
}
