package com.example.buith.project_prm.service;

import com.example.buith.project_prm.model.Account;
import com.example.buith.project_prm.model.AddressResponse;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.model.ProductResponse;
import com.example.buith.project_prm.model.ProductType;
import com.example.buith.project_prm.model.ProductTypeResponse;
import com.example.buith.project_prm.model.RegisterResponse;
import com.example.buith.project_prm.model.Token;
import com.example.buith.project_prm.utils.Define;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {

    @Headers({"authorization: Basic bXktY2xpZW50Om15LXNlY3JldA=="})
    @FormUrlEncoded
    @POST("oauth/token")
    Call<Token> login(@FieldMap Map<String, String> params);

    @GET("api/public/productTypes")
    Call<ProductTypeResponse> getProductTypes();

    // API đăng ký tài khoản
    @POST("api/public/reg")
    Call<RegisterResponse> register(@Body Account ac);

    // API lấy tất cả sản phẩm theo loại sản phẩm
    @GET("api/public/productsSearch/{typeID}")
    Call<ProductResponse> getProductInType(@Path("typeID") int typeID);

    // API lấy địa chỉ
    @GET("api/public/address")
    Call<AddressResponse> getAllAddress();

    // API thêm sản phẩm
    @POST("api/product")
    Call<ProductResponse> addProduct(@Body Product p, @Header("Authorization") String token);

    // API lấy tất cả sản phẩm trạng thái đang bán của người dùng
    @GET("api/public/user/productsActive/{username}")
    Call<ProductResponse> getProductActiveByUserName(@Path("username") String username);

    // API lấy tất cả sản phẩm trạng thái ngừng bán của người dùng
    @GET("api/public/user/productsDisable/{username}")
    Call<ProductResponse> getProductInactiveByUserName(@Path("username") String username);
}
