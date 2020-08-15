package com.example.authservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class JwtRequest {

    @NotBlank
    private final String jwt;

    public JwtRequest(@JsonProperty("jwt") String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
