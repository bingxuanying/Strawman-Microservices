package com.example.adminservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UpdateUserProductSetRequest {

    @NotNull
    private List<Integer> idSet;

    @NotBlank
    private String cliUsername;

    public UpdateUserProductSetRequest(@JsonProperty("idSet") List<Integer> idSet,
                                       @JsonProperty("cliUsername") String cliUsername) {
        this.idSet = idSet;
        this.cliUsername = cliUsername;
    }

    public List<Integer> getIdSet() {
        return idSet;
    }

    public void setIdSet(List<Integer> idSet) {
        this.idSet = idSet;
    }

    public String getCliUsername() {
        return cliUsername;
    }

    public void setCliUsername(String cliUsername) {
        this.cliUsername = cliUsername;
    }

    @Override
    public String toString() {
        return "UpdateUserProductSetRequest{" +
                "idSet=" + idSet +
                ", cliUsername='" + cliUsername + '\'' +
                '}';
    }
}
