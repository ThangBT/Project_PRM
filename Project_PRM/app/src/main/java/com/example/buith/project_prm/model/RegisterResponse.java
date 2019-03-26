package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("status")
    private int status;

    public RegisterResponse(int status) {
        this.status = status;
    }

    public RegisterResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
