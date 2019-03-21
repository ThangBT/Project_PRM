package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;


public class Product implements Serializable {
    @SerializedName("productID")
    private String productId;
    @SerializedName("productName")
    private String productName;
    @SerializedName("userNameSeller")
    private String userNameSeller;
    @SerializedName("typeID")
    private String typeId;
    @SerializedName("price")
    private Integer price;
    @SerializedName("status")
    private Integer status;
    @SerializedName("description")
    private String description;
    @SerializedName("imageID")
    private String imageId;
    @SerializedName("ratting")
    private String ratting;
    @SerializedName("adressSale")
    private String adressSale;
    @SerializedName("priority")
    private Integer priority;
    @SerializedName("numberOfDaysPriority")
    private Integer numberOfDaysPriority;
    @SerializedName("numberOfLike")
    private Integer numberOfLike;

    public Product() {
    }

    public Product(String productName, String typeId, Integer price) {
        this.productName = productName;
        this.typeId = typeId;
        this.price = price;
    }

    public Product(String productName, Integer price, String adressSale) {
        this.productName = productName;
        this.price = price;
        this.adressSale = adressSale;
    }

    public Product(String productId, String productName, String userNameSeller, String typeId, Integer price, Integer status, String description, String imageId, String ratting, String adressSale, Integer priority, Integer numberOfDaysPriority, Integer numberOfLike) {
        this.productId = productId;
        this.productName = productName;
        this.userNameSeller = userNameSeller;
        this.typeId = typeId;
        this.price = price;
        this.status = status;
        this.description = description;
        this.imageId = imageId;
        this.ratting = ratting;
        this.adressSale = adressSale;
        this.priority = priority;
        this.numberOfDaysPriority = numberOfDaysPriority;
        this.numberOfLike = numberOfLike;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserNameSeller() {
        return userNameSeller;
    }

    public void setUserNameSeller(String userNameSeller) {
        this.userNameSeller = userNameSeller;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getRatting() {
        return ratting;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    public String getAdressSale() {
        return adressSale;
    }

    public void setAdressSale(String adressSale) {
        this.adressSale = adressSale;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getNumberOfDaysPriority() {
        return numberOfDaysPriority;
    }

    public void setNumberOfDaysPriority(Integer numberOfDaysPriority) {
        this.numberOfDaysPriority = numberOfDaysPriority;
    }

    public Integer getNumberOfLike() {
        return numberOfLike;
    }

    public void setNumberOfLike(Integer numberOfLike) {
        this.numberOfLike = numberOfLike;
    }


}
