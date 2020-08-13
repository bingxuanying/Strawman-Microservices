package com.example.authservice.api;

import com.example.authservice.api.dto.UserDto;
import com.example.authservice.exception.ResBodyTemp;
import com.example.authservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class UserController {
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
    public @ResponseBody ResBodyTemp register(@RequestBody @Valid UserDto userDto) {
        userService.save((userDto));
        System.out.println(userDto.toString());
        return new ResBodyTemp("VALIDATION_SUCCESS", "successfully registered");
    }
}
