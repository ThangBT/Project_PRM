package com.example.buith.project_prm.service;

import com.example.buith.project_prm.model.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface LoginService {
//    @GET("/api/login")
//    Call<Account> login(@Query("username") String username, @Query("password") String password);
    @GET("https://postman-echo.com/get")
    Call<Object> login();
}
