package com.example.adminservice.repository;

import com.example.adminservice.entities.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
    Data findById(Long id);

    @Transactional
    void deleteById(Long id);
}
