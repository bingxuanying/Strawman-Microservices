package com.example.authservice.security.filters;

import com.example.authservice.entities.ERole;
import com.example.authservice.security.authentications.UsernamePasswordAuthentication;
import com.example.authservice.util.JwtUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

//      --------  Authentication  --------
        String bodyStr = IOUtils.toString(request.getInputStream(), Charset.defaultCharset());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode bodyJson = mapper.readTree(bodyStr);

        String username = bodyJson.get("username").textValue();
        String password = bodyJson.get("password").textValue();

        Authentication authentication = new UsernamePasswordAuthentication(username, password);
        authentication = authenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

//      --------  Generate New JWT  --------
        final String jwt = jwtUtil.generateToken(username);

//      --------  Redis  --------
        redisTemplate.opsForHash().put("jwt", username, jwt);

//      --------  Set Response Header  --------
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
            response.setHeader("Role", "admin");
        } else {
            response.setHeader("Role", "user");
        }

        response.setHeader("Authorization", jwt);
        response.setHeader("Auth", jwt);
        response.setHeader("Username", authentication.getName());
        response.setHeader("Company", authentication.getName());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
