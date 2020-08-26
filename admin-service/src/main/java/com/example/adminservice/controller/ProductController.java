package com.example.adminservice.controller;

import com.example.adminservice.payload.request.CreateProductRequest;
import com.example.adminservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "admin/product")
public class ProductController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/create")
    public void createProduct(@RequestBody @Valid CreateProductRequest request) {
        Integer num = request.getNum();
        for(int i = 0; i < num; i++) { userService.createProduct(request.getCompany()); }
    }

}
