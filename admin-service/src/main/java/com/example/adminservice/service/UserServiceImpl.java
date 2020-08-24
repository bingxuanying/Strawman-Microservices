package com.example.adminservice.service;

import com.example.adminservice.entities.Product;
import com.example.adminservice.entities.User;
import com.example.adminservice.repository.DataRepository;
import com.example.adminservice.repository.ProductRepository;
import com.example.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Object createProduct(String company) {
        Product product = new Product();

        if (!company.isBlank()) { return updateUserProductSet(product, company); }
        else { return productRepository.save(product); }
    }

    public Product getProductById(Integer i) {
        return productRepository.findById(Long.valueOf(i));
    }

    public List<Integer> getProductSetByCompany(String company) {

        User user = userRepository.findByCompany(company);
        List<Integer> productIdSet = new ArrayList<>();
        for (Product product : user.getProducts()) {
            Integer intValue = product.getId().intValue();
            productIdSet.add(intValue);
        }
        return productIdSet;
    }

    public User updateUserProductSet(Product product, String company) {

        User userToUpdate = userRepository.findByCompany(company);
        Set<Product> newProductSet = userToUpdate.getProducts();
        newProductSet.add(product);
        userToUpdate.setProducts(newProductSet);

        return userRepository.save(userToUpdate);
    }

    public User deleteUserProductSet(Integer id, String company) {

        Product product = productRepository.findById(Long.valueOf(id));
        User userToUpdate = userRepository.findByCompany(company);
        Set<Product> newProductSet = userToUpdate.getProducts();
        newProductSet.remove(product);
        userToUpdate.setProducts(newProductSet);

        return userRepository.save(userToUpdate);

    }
}
