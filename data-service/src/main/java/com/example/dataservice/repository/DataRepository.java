package com.example.dataservice.repository;

import com.example.dataservice.entities.Data;
import com.example.dataservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
    Data findByID(Long id);

    List<Data> findAllByProductID(Product product);

    @Transactional
    void deleteByID(Long id);
}
