package com.example.authservice.controller;

import com.example.authservice.entities.Data;
import com.example.authservice.entities.Product;
import com.example.authservice.entities.User;
import com.example.authservice.payload.request.RegisterRequest;
import com.example.authservice.payload.response.ResBodyTemp;
import com.example.authservice.repository.DataRepository;
import com.example.authservice.repository.ProductRepository;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.UserServiceImpl;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final String REDIS_INDEX_KEY = "JWT";

    @Autowired
    private UserServiceImpl userService;


    @PostMapping(value = "/register")
    public @ResponseBody ResBodyTemp register(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.save(registerRequest);
        return new ResBodyTemp("VALIDATION_SUCCESS", "successfully registered");
    }

//    @GetMapping(value = "/product")
//    public void createProduct() {
//        Product product = new Product();
//        productRepository.save(product);
//    }
//
//    @GetMapping(value = "/data")
//    public void createData() {
//        Data data = new Data();
//        data.setFilePath("/a");
//        Product product = productRepository.findById(Long.valueOf(1));
//        data.setProduct(product);
//        dataRepository.save(data);
//    }
//
//    @DeleteMapping(value = "/data")
//    public void deleteData() {
//        Product product = productRepository.findById(Long.valueOf(1));
//        Set<Data> dataToUpdate = product.getData();
//
//        Data dataToRemove = dataRepository.findById(Long.valueOf(1));
//        dataToUpdate.remove(dataToRemove);
//        product.setData(dataToUpdate);
//
//        productRepository.save(product);
//    }
//
//
//
//    @GetMapping(value = "/data1")
//    public void cretaeData1() {
//        Data data = new Data();
//        data.setFilePath("/a");
//        dataRepository.save(data);
//
//        Product product = productRepository.findById(Long.valueOf(1));
//        Set<Data> lst = product.getData();
//        lst.add(data);
//        product.setData(lst);
//
//        System.out.println(product.toString());
//
//        productRepository.save(product);
//    }
//
//    @GetMapping(value = "/test")
//    public void output() {
//        Product product = productRepository.findById(Long.valueOf(1));
//        System.out.println(product.toString());
//    }
//
//    @PostMapping(value = "/authenticate")
//    public boolean authenticateJwt(@RequestBody @Valid JwtRequest jwtRequest) {
//        return jwtUtil.validateToken(jwtRequest.getJwt());
//    }
}
