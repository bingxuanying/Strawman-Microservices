package com.example.dataservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class FetchRecordsRequest {

    @NotNull
    private Integer id;

    public FetchRecordsRequest(@JsonProperty("id") Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FetchRecordsRequest{" +
                "id=" + id +
                '}';
    }
}
