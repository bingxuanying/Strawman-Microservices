package com.example.authservice.controller;

import com.example.authservice.payload.request.RegisterRequest;
import com.example.authservice.payload.response.ResBodyTemp;
import com.example.authservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "auth")
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/register")
    public ResBodyTemp register(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.save(registerRequest);
        return new ResBodyTemp("VALIDATION_SUCCESS", "successfully registered");
    }

}
