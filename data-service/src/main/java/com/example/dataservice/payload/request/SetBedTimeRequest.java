package com.example.dataservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class SetBedTimeRequest {

    @NotNull
    private Integer milisec;

    public SetBedTimeRequest(@JsonProperty("bedtime") Integer milisec) {
        this.milisec = milisec;
    }

    public Integer getMilisec() {
        return milisec;
    }

    public void setMilisec(Integer milisec) {
        this.milisec = milisec;
    }

    @Override
    public String toString() {
        return "setBedTimeRequest{" +
                "milisec=" + milisec +
                '}';
    }
}
