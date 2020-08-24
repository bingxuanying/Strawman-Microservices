package com.example.adminservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CreateProductRequest {

    @NotNull
    private int num;

    private String company;

    public CreateProductRequest(@JsonProperty("num") int num,
                                @JsonProperty("company") String company) {
        this.num = num;
        this.company = company == null ? " " : company;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "num=" + num +
                ", company='" + company + '\'' +
                '}';
    }
}
