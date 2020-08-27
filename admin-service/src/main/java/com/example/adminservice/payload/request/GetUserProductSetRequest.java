package com.example.adminservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class GetUserProductSetRequest {

    @NotBlank
    private String cliUsername;

    public GetUserProductSetRequest(@JsonProperty("cliUsername") String cliUsername) {
        this.cliUsername = cliUsername;
    }

    public String getCliUsername() {
        return cliUsername;
    }

    public void setCliUsername(String cliUsername) {
        this.cliUsername = cliUsername;
    }

    @Override
    public String toString() {
        return "GetProductsResponse{" +
                "cliUsername='" + cliUsername + '\'' +
                '}';
    }
}
