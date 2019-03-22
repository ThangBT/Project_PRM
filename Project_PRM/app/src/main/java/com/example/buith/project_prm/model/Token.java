package com.example.buith.project_prm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Token implements Serializable {
    @SerializedName("access_token")
    @Expose
    private String access_token;
    @SerializedName("token_type")
    private String token_type;
    @SerializedName("refresh_token")
    private String refresh_token;
    @SerializedName("scope")
    private String scope;

    public Token() {
    }

    public Token(String access_token, String token_type, String refresh_token, String scope) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.scope = scope;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
