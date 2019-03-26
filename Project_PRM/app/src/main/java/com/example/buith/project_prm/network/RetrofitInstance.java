package com.example.buith.project_prm.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.service.ApiClient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://10.0.2.2:8080/";

    public static ApiClient getRetrofitInstance(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constant.KeySharedPreference.USER_LOGIN, context.MODE_PRIVATE);
        String tmp = pref.getString(Constant.KeySharedPreference.ACCESS_TOKEN, null);
        final String accessToken = getAccessToken(tmp);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .addHeader("Content-Type", "application/json")

                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()

                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiClient.class);
    }

    public static String getAccessToken(String tmp){
        if(tmp != null){
            return "Bearer "+ tmp;
        }else{
            return "Basic bXktY2xpZW50Om15LXNlY3JldA==";
        }
    }
}
