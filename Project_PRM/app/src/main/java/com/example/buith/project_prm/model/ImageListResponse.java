package com.example.buith.project_prm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageListResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private List<Image> list;

    public ImageListResponse(int status, List<Image> list) {
        this.status = status;
        this.list = list;
    }

    public ImageListResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Image> getList() {
        return list;
    }

    public void setList(List<Image> list) {
        this.list = list;
    }
}
