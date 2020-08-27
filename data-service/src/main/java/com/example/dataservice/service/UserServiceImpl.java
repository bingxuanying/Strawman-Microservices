package com.example.dataservice.service;

import com.example.dataservice.entities.Data;
import com.example.dataservice.entities.Product;
import com.example.dataservice.entities.User;
import com.example.dataservice.models.DataModel;
import com.example.dataservice.payload.response.DataSetResponse;
import com.example.dataservice.repository.DataRepository;
import com.example.dataservice.repository.ProductRepository;
import com.example.dataservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    DataRepository dataRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Integer> fetchTrapIDs(String username) {

        User user = userRepository.findByUsername(username);

        List<Integer> productIdSet = new ArrayList<>();
        for (Product product : user.getProducts()) {
            Integer id = product.getId().intValue();
            productIdSet.add(id);
        }
        return productIdSet;
    }

    public List<DataModel> fetchRecordsByID(Integer trapID) {

        Product product = productRepository.findById(Long.valueOf(trapID));
        List<Data> dataSet = dataRepository.findAllByProduct(product);

        List<DataModel> newDataSet = new ArrayList<>();
        for (Data data : dataSet) {
            System.out.println(data.getFilePath());
            DataModel newData = new DataModel(data.getTimestamp(),
                    data.getFilePath(),
                    data.getCroppedFilePath(),
                    data.getTrapLocation(),
                    data.getHumidity(),
                    data.getTemperature(),
                    data.getParticleCount());
            newDataSet.add(newData);
        }

        return newDataSet;
    }

    public void setBedTime(String username, Integer milisec) {

        User userToUpdate = userRepository.findByUsername(username);

        if (userToUpdate.getBedTime().equals(milisec)) { return ; }

        userToUpdate.setBedTime(milisec);

        userRepository.save(userToUpdate);
    }
}
