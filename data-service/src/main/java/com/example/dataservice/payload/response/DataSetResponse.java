package com.example.dataservice.payload.response;

import com.example.dataservice.models.DataModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSetResponse {

    private List<DataModel> dataSet;

    public DataSetResponse(List<DataModel> dataSet) {
        this.dataSet = dataSet;
    }

    public List<DataModel> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<DataModel> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public String toString() {
        return "DataSetResponse{" +
                "dataSet=" + dataSet +
                '}';
    }
}
