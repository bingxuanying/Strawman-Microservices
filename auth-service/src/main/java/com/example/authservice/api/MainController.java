package com.example.authservice.api;

import com.example.authservice.payload.request.JwtRequest;
import com.example.authservice.payload.request.LoginRequest;
import com.example.authservice.payload.request.RegisterRequest;
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
    public @ResponseBody ResBodyTemp register(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.save(registerRequest);
        return new ResBodyTemp("VALIDATION_SUCCESS", "successfully registered");
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final String jwt = jwtUtil.generateToken(userService.loadUserByUsername(loginRequest.getUsername()));

        /*
         * TODO: save token to Redis
         */

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(value = "/authenticate")
    public boolean authenticateJwt(@RequestBody @Valid JwtRequest jwtRequest) {
        return jwtUtil.validateToken(jwtRequest.getJwt());
    }
}
