package com.example.buith.project_prm.network;

import com.example.buith.project_prm.model.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {
    @GET("/api/login")
    Call<Account> login(@Query("username") String username, @Query("password") String password);
}
