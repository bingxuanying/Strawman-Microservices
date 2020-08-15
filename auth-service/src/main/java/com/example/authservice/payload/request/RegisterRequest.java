package com.example.authservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RegisterRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String company;

    private ArrayList<Integer> products;

    @NotBlank
    private String password;

    private Set<String> roles;

    public RegisterRequest(@JsonProperty("username") String username,
                           @JsonProperty("company") String company,
                           @JsonProperty("products") ArrayList<Integer> products,
                           @JsonProperty("password")  String password,
                           @JsonProperty("roles") Set<String> roles) {
        this.username = username;
        this.company = company;
        this.products = (ArrayList<Integer>) products;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", company='" + company + '\'' +
                ", products=" + products +
                ", password='" + password + '\'' +
                '}';
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ArrayList<Integer> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Integer> products) {
        this.products = products;
    }

}
