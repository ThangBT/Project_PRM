package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductUserResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private List<Product> list;

    public ProductUserResponse(int status, List<Product> list) {
        this.status = status;
        this.list = list;
    }

    public ProductUserResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
