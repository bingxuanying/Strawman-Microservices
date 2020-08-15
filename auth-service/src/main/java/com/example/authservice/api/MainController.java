package com.example.authservice.api;

import com.example.authservice.payload.request.LoginReq;
import com.example.authservice.payload.request.RegisterReq;
import com.example.authservice.payload.response.AuthenticationResponse;
import com.example.authservice.payload.response.ResBodyTemp;
import com.example.authservice.service.UserService;
import com.example.authservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class MainController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    private UserService userService;

    public MainController(UserService userService) {
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
        return new ResBodyTemp("VALIDATION_SUCCESS", "successfully registered");
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginReq loginReq) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final String jwt = jwtUtil.generateToken(userService.loadUserByUsername(loginReq.getUsername()));

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
