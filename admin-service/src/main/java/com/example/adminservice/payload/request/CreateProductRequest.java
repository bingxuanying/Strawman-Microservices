package com.example.adminservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CreateProductRequest {

    @NotNull
    private int num;

    private String cliUsername;

    public CreateProductRequest(@JsonProperty("num") int num,
                                @JsonProperty("cliUsername") String cliUsername) {
        this.num = num;
        this.cliUsername = cliUsername == null ? " " : cliUsername;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCliUsername() {
        return cliUsername;
    }

    public void setCliUsername(String cliUsername) {
        this.cliUsername = cliUsername;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "num=" + num +
                ", cliUsername='" + cliUsername + '\'' +
                '}';
    }
}
