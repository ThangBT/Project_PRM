package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;


public class Product implements Serializable {
    @SerializedName("productID")
    private int productID;
    @SerializedName("productName")
    private String productName;
    @SerializedName("typeID")
    private int typeID;
    @SerializedName("price")
    private Long price;
    @SerializedName("userName")
    private String userName;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("status")
    private boolean status;
    @SerializedName("addressID")
    private int addressID;
    @SerializedName("priority")
    private int priority;
    @SerializedName("numberOfDaysPriority")
    private int numberOfDaysPriority;
    @SerializedName("images")
    private List<Image> images;

    public Product() {
    }

    public Product(String productName, int typeId, Long price) {
        this.productName = productName;
        this.typeID = typeId;
        this.price = price;
    }

    public Product(String productName, Long price, int adressSale) {
        this.productName = productName;
        this.price = price;
        this.addressID = adressSale;
    }

    public Product(int productID, String productName, int typeID, Long price, String userName, String description, String image, boolean status, int addressID, int priority, int numberOfDaysPriority, List<Image> images) {
        this.productID = productID;
        this.productName = productName;
        this.typeID = typeID;
        this.price = price;
        this.userName = userName;
        this.description = description;
        this.image = image;
        this.status = status;
        this.addressID = addressID;
        this.priority = priority;
        this.numberOfDaysPriority = numberOfDaysPriority;
        this.images = images;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getNumberOfDaysPriority() {
        return numberOfDaysPriority;
    }

    public void setNumberOfDaysPriority(int numberOfDaysPriority) {
        this.numberOfDaysPriority = numberOfDaysPriority;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
