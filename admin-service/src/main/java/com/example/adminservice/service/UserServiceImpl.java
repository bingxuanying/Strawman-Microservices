package com.example.adminservice.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.adminservice.entities.Data;
import com.example.adminservice.entities.Product;
import com.example.adminservice.entities.User;
import com.example.adminservice.repository.DataRepository;
import com.example.adminservice.repository.ProductRepository;
import com.example.adminservice.repository.UserRepository;
import com.example.adminservice.security.model.SecurityUser;
import com.example.adminservice.util.AWSS3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DataRepository dataRepository;

    @Autowired
    AWSS3Util awsS3Util;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Integer> getProductIds() {
        List<Product> productSet = productRepository.findAll();
        List<Integer> idSet = new ArrayList<>();

        for (Product product : productSet) {
            idSet.add(Math.toIntExact(product.getId()));
        }

        return idSet;
    }

    public Object createProduct(String cliUsername) {
        Product product = new Product();

        if (!cliUsername.isBlank()) { return updateUserProductSet(product, cliUsername); }
        else { return productRepository.save(product); }
    }

    public Product getProductById(Integer i) {
        return productRepository.findById(Long.valueOf(i));
    }

    public List<Integer> getProductSetByUsername(String cliUsername) {

        User user = userRepository.findByUsername(cliUsername);
        List<Integer> productIdSet = new ArrayList<>();
        for (Product product : user.getProducts()) {
            Integer intValue = product.getId().intValue();
            productIdSet.add(intValue);
        }
        return productIdSet;
    }

    public User updateUserProductSet(Product product, String cliUsername) {

        User userToUpdate = userRepository.findByUsername(cliUsername);
        Set<Product> newProductSet = userToUpdate.getProducts();
        newProductSet.add(product);
        userToUpdate.setProducts(newProductSet);

        return userRepository.save(userToUpdate);
    }

    public User deleteUserProductSet(Integer id, String cliUsername) {

        Product product = productRepository.findById(Long.valueOf(id));
        User userToUpdate = userRepository.findByUsername(cliUsername);
        Set<Product> newProductSet = userToUpdate.getProducts();
        newProductSet.remove(product);
        userToUpdate.setProducts(newProductSet);

        return userRepository.save(userToUpdate);
    }

    public String saveData(Integer id, String location, Double temperature, Double humidity) {

        Product product = productRepository.findById(Long.valueOf(id));

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("PST"));
        String timestamp = formatter.format(date);

        Random random = new Random();
        String rdm = String.valueOf(random.nextInt(9000) + 1000);
        String fileName = timestamp.replace(':', '-') + 'N' + id + 'R' + rdm + ".jpeg";

        String fileURL = "https://s3-us-west-1.amazonaws.com/grain.storage.project/original/" + fileName;
//        String croppedFileURL = "https://s3-us-west-1.amazonaws.com/grain.storage.project/images/cropped-" + fileName;

        Data data = new Data(timestamp,
                fileURL,
                "unknown",
                location,
                humidity,
                temperature,
                -1,
                product);

        dataRepository.save(data);

        return fileName;
    }

    public void uploadImage(HttpServletRequest request, String filename) throws IOException {


//        boolean output = String.valueOf(rawData.length) == request.getHeader("Content-Length");
//        System.out.println(output);

        byte [] rawData = request.getInputStream().readAllBytes();

        Long contentLength = Long.valueOf(request.getHeader("Content-Length"));
        contentLength = rawData[0] == 216 && rawData[1] == 255 ? contentLength + 1 : contentLength;
        String contentType = request.getHeader("Content-Type");

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        metadata.setContentType(contentType);

        awsS3Util.uploadOriginalImage(filename, writeImage(request, filename), metadata);

    }

    private ByteArrayInputStream writeImage(HttpServletRequest request, String filename) throws IOException {
        byte [] rawData = request.getInputStream().readAllBytes();

        ByteArrayOutputStream agent = new ByteArrayOutputStream();
        agent.write(rawData);
        if (rawData[0] == 216 && rawData[1] == 255) {
            byte[] header = { (byte) 0xff };
            agent.write(header);
        }
        byte[] data = agent.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        return bis;

//        BufferedImage bImage = ImageIO.read(bis);
//        return ImageIO.write(bImage, "jpeg", new File(filename) );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invaild username or password");
        }

        return new SecurityUser(user);
    }
}
