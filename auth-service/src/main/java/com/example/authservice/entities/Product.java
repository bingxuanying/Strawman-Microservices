package com.example.authservice.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "product",
            orphanRemoval = true)
    private Set<Data> data = new HashSet<>();

    public Product() {
    }

    public Product(Set<Data> data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Data> getData() {
        return data;
    }

    public void setData(Set<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }
}
