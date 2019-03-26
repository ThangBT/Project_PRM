package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

public class AccountResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private Account data;

    public AccountResponse(int status, Account data) {
        this.status = status;
        this.data = data;
    }

    public AccountResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Account getData() {
        return data;
    }

    public void setData(Account data) {
        this.data = data;
    }
}
