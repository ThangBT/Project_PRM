package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductTypeResponse  {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private List<ProductType> listProductType;

    public ProductTypeResponse() {
    }

    public ProductTypeResponse(int status, List<ProductType> listProductType) {
        this.status = status;
        this.listProductType = listProductType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ProductType> getListProductType() {
        return listProductType;
    }

    public void setListProductType(List<ProductType> listProductType) {
        this.listProductType = listProductType;
    }
}
