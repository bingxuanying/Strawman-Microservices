package com.example.dataservice.models;

public class DataModel {

    private String timestamp;

    private String filePath;

    private String croppedFilePath;

    private String trapLocation;

    private Double humidity;

    private Double temperature;

    private Integer particleCount;

    public DataModel(String timestamp, String filePath, String croppedFilePath, String trapLocation, Double humidity, Double temperature, Integer particleCount) {
        this.timestamp = timestamp;
        this.filePath = filePath;
        this.croppedFilePath = croppedFilePath;
        this.trapLocation = trapLocation;
        this.humidity = humidity;
        this.temperature = temperature;
        this.particleCount = particleCount;
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

    @Override
    public String toString() {
        return "DataModel{" +
                "timestamp='" + timestamp + '\'' +
                ", filePath='" + filePath + '\'' +
                ", croppedFilePath='" + croppedFilePath + '\'' +
                ", trapLocation='" + trapLocation + '\'' +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", particleCount=" + particleCount +
                '}';
    }
}
