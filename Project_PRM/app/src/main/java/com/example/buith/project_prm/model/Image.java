package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("productID")
    private int productID;
    @SerializedName("image")
    private String image;

    public Image() {
    }

    public Image(int productID, String image) {
        this.productID = productID;
        this.image = image;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
