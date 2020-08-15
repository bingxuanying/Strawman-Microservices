package com.example.authservice.api;

import com.example.authservice.payload.request.LoginReq;
import com.example.authservice.payload.request.RegisterReq;
import com.example.authservice.payload.response.ResBodyTemp;
import com.example.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public String test() {
        return "Hello there";
    }

    @PostMapping(value = "/register")
    public @ResponseBody ResBodyTemp register(@RequestBody @Valid RegisterReq registerReq) {
        userService.save(registerReq);
        System.out.println(registerReq.toString());
        return new ResBodyTemp("VALIDATION_SUCCESS", "successfully registered");
    }

    @PostMapping(value = "/signin")
    public ResBodyTemp authenticateUser(@RequestBody @Valid LoginReq loginReq) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));

        System.out.println(loginReq.getUsername());
        System.out.println(loginReq.getPassword());
        System.out.println(authentication.isAuthenticated());

        if (authentication.isAuthenticated()) SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResBodyTemp("VALIDATION_SUCCESS", "successfully signed in");
    }
}
