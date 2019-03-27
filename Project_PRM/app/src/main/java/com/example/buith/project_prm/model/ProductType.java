package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

public class ProductType {
    @SerializedName("typeID")
    private int typeId;
    @SerializedName("typeName")
    private String typeName;
    @SerializedName("typeImage")
    private String typeImage;

    public ProductType(int typeId, String typeName, String typeImage) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeImage = typeImage;
    }

    public ProductType() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
