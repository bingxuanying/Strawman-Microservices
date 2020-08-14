package com.example.authservice.service;

import com.example.authservice.model.Role;
import com.example.authservice.payload.request.RegisterReq;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(RegisterReq registerReq) {
        User user = new User(registerReq.getUsername(),
                registerReq.getCompany(),
                (ArrayList<Integer>) registerReq.getProducts(),
                passwordEncoder.encode(registerReq.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));

//        if (userRepository.existsById((long) 1)) {
//            var find = userRepository.getOne((long) 1);
//            Set<Integer> set = new LinkedHashSet<>(find.getProducts());
//
//            List<Integer> newList = new ArrayList<>(Arrays.asList(1,2,3));
//            set.addAll(newList);
//            List<Integer> combinedList = new ArrayList<>(set);
//
//            System.out.println("old: " + find.getProducts());
//            System.out.println("new: " + combinedList);
//        }

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invaild username or password");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
