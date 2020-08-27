package com.example.adminservice.security.providers;

import com.example.adminservice.security.authentications.TokenAuthentication;
import com.example.adminservice.service.UserServiceImpl;
import com.example.adminservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = authentication.getName();
        String username = jwtUtil.extractUsername(token);

        boolean isExist = redisTemplate.opsForHash().hasKey("jwt", username) &&
                redisTemplate.opsForHash().get("jwt", username).equals(token);
        boolean isValid = jwtUtil.validateToken(token);

        if (isExist && isValid) {
            UserDetails user = userService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(username, token, user.getAuthorities());
        } else {
            if (!isValid) { redisTemplate.opsForHash().delete("jwt", jwtUtil.extractUsername(token)); }
            throw new BadCredentialsException("Token is invalid");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}
