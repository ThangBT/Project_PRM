package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address implements Serializable {
    @SerializedName("addressID")
    private int addressID;
    @SerializedName("addressName")
    private String addressName;

    public Address() {
    }

    public Address(int addressID, String addressName) {
        this.addressID = addressID;
        this.addressName = addressName;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Override
    public String toString() {
        return this.addressName;
    }
}
