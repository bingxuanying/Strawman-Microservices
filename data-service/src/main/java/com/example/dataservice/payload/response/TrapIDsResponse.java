package com.example.dataservice.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrapIDsResponse {

    private List<Integer> IdArr;

    public TrapIDsResponse(List<Integer> idArr) {
        IdArr = idArr;
    }

    public List<Integer> getIdArr() {
        return IdArr;
    }

    public void setIdArr(List<Integer> idArr) {
        IdArr = idArr;
    }

    @Override
    public String toString() {
        return "TrapIDResponse{" +
                "IdArr=" + IdArr +
                '}';
    }
}
