package com.example.adminservice.controller;

import com.example.adminservice.entities.Product;
import com.example.adminservice.entities.User;
import com.example.adminservice.payload.request.GetUserProductSetRequest;
import com.example.adminservice.payload.request.UpdateUserProductSetRequest;
import com.example.adminservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "admin/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/productSet")
    public List<Integer> getUserProductSet(@RequestBody @Valid GetUserProductSetRequest request) {
        List<Integer> IdArr = userService.getProductSetByCompany(request.getCompany());
        Collections.sort(IdArr);
        return IdArr;
    }

    @PutMapping(value = "/productSet")
    public void updateUserProductSet(@RequestBody @Valid UpdateUserProductSetRequest request) {
        for (Integer id : request.getIdArr()) {
            Product product = userService.getProductById(id);
            userService.updateUserProductSet(product, request.getCompany());
        }
    }

    @DeleteMapping(value = "/productSet")
    public void deleteUserProductSet(@RequestBody @Valid UpdateUserProductSetRequest request) {
        for (Integer id : request.getIdArr()) {
            userService.deleteUserProductSet(id, request.getCompany());
        }
    }
}
