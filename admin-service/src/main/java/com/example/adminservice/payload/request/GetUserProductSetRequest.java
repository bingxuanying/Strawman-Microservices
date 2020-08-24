package com.example.adminservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class GetUserProductSetRequest {

    @NotBlank
    private String company;

    public GetUserProductSetRequest(@JsonProperty("company") String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "GetProductsResponse{" +
                "company='" + company + '\'' +
                '}';
    }
}
