package com.example.dataservice.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrapIDsResponse {

    private List<Integer> idSet;

    public TrapIDsResponse(List<Integer> idSet) {
        this.idSet = idSet;
    }

    public List<Integer> getIdSet() {
        return idSet;
    }

    public void setIdSet(List<Integer> idSet) {
        this.idSet = idSet;
    }

    @Override
    public String toString() {
        return "TrapIDsResponse{" +
                "idSet=" + idSet +
                '}';
    }
}
