package com.example.authservice.api;

import com.example.authservice.api.dto.UserDto;
import com.example.authservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public String showRegistrationForm() {
        return "Hello there";
    }

    @PostMapping
    public String registerUserAccount(@RequestBody UserDto userDto) {
//        userService.save((userDto));
        System.out.println(userDto);
        return "redirect:/registration?success";
    }
}
