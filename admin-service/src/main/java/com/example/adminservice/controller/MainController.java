package com.example.adminservice.controller;

import com.example.adminservice.entities.Product;
import com.example.adminservice.entities.User;
import com.example.adminservice.payload.request.CreateProductRequest;
import com.example.adminservice.payload.request.GetUserProductSetRequest;
import com.example.adminservice.payload.request.UpdateUserProductSetRequest;
import com.example.adminservice.repository.DataRepository;
import com.example.adminservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "admin")
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/userProfiles")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    Create new product
    @PostMapping(value = "/create")
    public void createProduct(@RequestBody @Valid CreateProductRequest request) {
        Integer num = request.getNum();
        for(int i = 0; i < num; i++) { userService.createProduct(request.getCliUsername()); }
    }

//    Fetch all product IDs
    @GetMapping(value = "/productSet")
    public List<Integer> getProductIds() {
        return userService.getProductIds();
    }

//    Fetch product set from specific user
    @PostMapping(value = "/productSet")
    public List<Integer> getCompanyProductSet(@RequestBody @Valid GetUserProductSetRequest request) {
        List<Integer> IdArr = userService.getProductSetByUsername(request.getCliUsername());
        Collections.sort(IdArr);
        return IdArr;
    }

//    Assign product to specific user
    @PutMapping(value = "/productSet")
    public void updateUserProductSet(@RequestBody @Valid UpdateUserProductSetRequest request) {
        for (Integer id : request.getIdSet()) {
            Product product = userService.getProductById(id);
            userService.updateUserProductSet(product, request.getCliUsername());
        }
    }

//    Remove product from specific user
    @DeleteMapping(value = "/productSet")
    public void deleteUserProductSet(@RequestBody @Valid UpdateUserProductSetRequest request) {
        for (Integer id : request.getIdSet()) {
            userService.deleteUserProductSet(id, request.getCliUsername());
        }
    }

//    Receive new data
    @PostMapping(value = "/uploadImage")
    public boolean saveDataNImage(@RequestParam(name = "id") String idParam,
                              @RequestParam(name = "location") String location,
                              @RequestParam(name = "temperature") String temperatureParam,
                              @RequestParam(name = "humidity") String humidityParam,
                              HttpServletRequest request) throws IOException {

        Integer productId = Integer.parseInt(idParam);
        Double temperature = Double.parseDouble(temperatureParam);
        Double humidity = Double.parseDouble(humidityParam);

        String filename = userService.saveData(productId, location, temperature, humidity);
        userService.uploadImage(request, filename);
        return true;
    }
}
