package com.example.buith.project_prm.service;

import com.example.buith.project_prm.model.Account;
import com.example.buith.project_prm.model.Token;
import com.example.buith.project_prm.utils.Define;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface LoginService {

    //@Headers({"authorization: Basic bXktY2xpZW50Om15LXNlY3JldA==","Content-Type: application/x-www-form-urlencoded" })
    @FormUrlEncoded
    @POST("oauth/token")
    Call<Token> login(@FieldMap Map<String, String> params);
}
