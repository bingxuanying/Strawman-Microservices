package com.example.authservice.service;

import com.example.authservice.model.ERole;
import com.example.authservice.model.Role;
import com.example.authservice.payload.request.RegisterRequest;
import com.example.authservice.model.User;
import com.example.authservice.repository.RoleRepository;
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
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User save(RegisterRequest registerRequest) {

        Set<String> strRoles = registerRequest.getRoles();
        Set<Role> roles = new HashSet<>();

//        Recognize Registration Role
        if (strRoles == null) {
            System.out.println(ERole.ROLE_USER);
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role(Admin) is not found"));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role(Mod) is not found"));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role(User) is not found"));
                        roles.add(userRole);
                }
            });
        }

        User user = new User(registerRequest.getUsername(),
                registerRequest.getCompany(),
                (ArrayList<Integer>) registerRequest.getProducts(),
                passwordEncoder.encode(registerRequest.getPassword()),
                roles);

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
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
    }
}
