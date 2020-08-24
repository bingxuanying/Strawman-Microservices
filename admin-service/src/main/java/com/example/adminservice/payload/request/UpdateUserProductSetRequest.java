package com.example.adminservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UpdateUserProductSetRequest {

    @NotNull
    private List<Integer> idArr;

    @NotBlank
    private String company;

    public UpdateUserProductSetRequest(@JsonProperty("idArr") List<Integer> idArr, @JsonProperty("company") String company) {
        this.idArr = idArr;
        this.company = company;
    }

    public List<Integer> getIdArr() {
        return idArr;
    }

    public void setIdArr(List<Integer> idArr) {
        this.idArr = idArr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UpdateUserProductSetRequest{" +
                "idArr=" + idArr +
                ", company='" + company + '\'' +
                '}';
    }
}
