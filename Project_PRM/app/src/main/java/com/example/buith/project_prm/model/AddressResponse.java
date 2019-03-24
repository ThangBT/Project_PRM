package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private List<Address> list;

    public AddressResponse(int status, List<Address> list) {
        this.status = status;
        this.list = list;
    }

    public AddressResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Address> getList() {
        return list;
    }

    public void setList(List<Address> list) {
        this.list = list;
    }
}
