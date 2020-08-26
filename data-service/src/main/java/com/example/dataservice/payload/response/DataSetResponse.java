package com.example.dataservice.payload.response;

import com.example.dataservice.models.DataModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSetResponse {

    private List<DataModel> dataSet;

    public DataSetResponse(List<DataModel> idArr) {
        dataSet = idArr;
    }

    public List<DataModel> getIdArr() {
        return dataSet;
    }

    public void setIdArr(List<DataModel> idArr) {
        dataSet = idArr;
    }

    @Override
    public String toString() {
        return "TrapIDResponse{" +
                "dataSet=" + dataSet +
                '}';
    }
}
