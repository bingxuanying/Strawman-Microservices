package com.example.authservice.controller;

import com.example.authservice.payload.request.RegisterRequest;
import com.example.authservice.payload.response.ResBodyTemp;
import com.example.authservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class MainController {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtUtil jwtUtil;

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    private static final String REDIS_INDEX_KEY = "JWT";

    @Autowired
    private UserServiceImpl userService;

//    public MainController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping(value = "/register")
    public @ResponseBody ResBodyTemp register(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.save(registerRequest);
        return new ResBodyTemp("VALIDATION_SUCCESS", "successfully registered");
    }

//    @PostMapping(value = "/signin")
//    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        if (authentication.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        else { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password"); }
//
//        final String jwt = jwtUtil.generateToken(userService.loadUserByUsername(loginRequest.getUsername()));
//
//        /*
//         * TODO: test save token to Redis
//         */
////        redisTemplate.opsForHash().put(REDIS_INDEX_KEY, 1,  jwt);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }

//    @PostMapping(value = "/authenticate")
//    public boolean authenticateJwt(@RequestBody @Valid JwtRequest jwtRequest) {
//        return jwtUtil.validateToken(jwtRequest.getJwt());
//    }
}
