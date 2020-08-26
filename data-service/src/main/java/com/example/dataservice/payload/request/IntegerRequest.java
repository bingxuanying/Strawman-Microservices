package com.example.dataservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class IntegerRequest {

    @NotNull
    Integer ID;

    public IntegerRequest(@JsonProperty("id") Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "fetchRecordsRequest{" +
                "ID=" + ID +
                '}';
    }
}
