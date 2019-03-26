package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ProductResponse {
    @SerializedName("status")
    private Integer status;
    @SerializedName("data")
    private ArrayList<Product> productList;

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
