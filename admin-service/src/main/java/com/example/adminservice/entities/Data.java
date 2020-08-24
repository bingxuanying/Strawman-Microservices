package com.example.adminservice.entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timestamp;

    private String filePath;

    private String croppedFilePath;

    private String trapLocation;

    private Double humidity;

    private Double temperature;

    private Integer particleCount;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "product")
    private Product product;

    public Data() {
    }

    public Data(String timestamp, String filePath, String croppedFilePath, String trapLocation, Double humidity, Double temperature, Integer particleCount, Product product) {
        this.timestamp = timestamp;
        this.filePath = filePath;
        this.croppedFilePath = croppedFilePath;
        this.trapLocation = trapLocation;
        this.humidity = humidity;
        this.temperature = temperature;
        this.particleCount = particleCount;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCroppedFilePath() {
        return croppedFilePath;
    }

    public void setCroppedFilePath(String croppedFilePath) {
        this.croppedFilePath = croppedFilePath;
    }

    public String getTrapLocation() {
        return trapLocation;
    }

    public void setTrapLocation(String trapLocation) {
        this.trapLocation = trapLocation;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getParticleCount() {
        return particleCount;
    }

    public void setParticleCount(Integer particleCount) {
        this.particleCount = particleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
