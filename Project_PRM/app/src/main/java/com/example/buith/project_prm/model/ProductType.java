package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

public class ProductType {
    @SerializedName("typeID")
    private String typeId;
    @SerializedName("typeName")
    private String typeName;

    public ProductType(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public ProductType() {
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
