package com.example.authservice.controller;

import com.example.authservice.payload.request.LoginRequest;
import com.example.authservice.payload.request.RegisterRequest;
import com.example.authservice.payload.response.ResBodyTemp;
import com.example.authservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/register")
    public ResBodyTemp register(@RequestBody @Valid RegisterRequest request) {
        userService.save(request);
        return new ResBodyTemp("VALIDATION_SUCCESS", "successfully registered");
    }

//    @PostMapping(value = "/login")
//    public String login(Authentication authentication, @RequestBody @Valid LoginRequest request) {
//        return authentication.isAuthenticated()? "successfully login" : "failed to login";
//    }
}
