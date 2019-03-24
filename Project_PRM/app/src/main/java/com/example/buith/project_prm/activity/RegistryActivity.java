package com.example.buith.project_prm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.Account;
import com.example.buith.project_prm.model.OnDataLoaded;
import com.example.buith.project_prm.model.ProductType;
import com.example.buith.project_prm.model.ProductTypeResponse;
import com.example.buith.project_prm.model.RegisterResponse;
import com.example.buith.project_prm.network.RetrofitInstance;
import com.example.buith.project_prm.service.ApiClient;
import com.example.buith.project_prm.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistryActivity extends BaseActivity  {
    @BindView(R.id.edUsername)
     EditText edUsername;
    @BindView(R.id.edFullname)
     EditText edFullname;
    @BindView(R.id.edPassword)
     EditText edPassword;
    @BindView(R.id.edEmail)
     EditText edEmail;
    @BindView(R.id.edPhone)
     EditText edPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRegister)
    public void signIn(View view) {
        if(validateForm()){
            Account ac = new Account();
            ac.setUsername(edUsername.getText().toString());
            ac.setFullname(edFullname.getText().toString());
            ac.setPassword(edPassword.getText().toString());
            ac.setEmail(edEmail.getText().toString());
            ac.setPhoneNumber(edPhone.getText().toString());
            ApiClient apiClient = RetrofitInstance.getRetrofitInstance(this);
            Call<RegisterResponse> call = apiClient.register(ac) ;
            if(isNetworkAvailable()) {
                showLoading();
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        hideLoading();
                        RegisterResponse res = response.body();
                        if(res.getStatus() == 0) {
                            Toast.makeText(RegistryActivity.this, Constant.Message.ERROR_REGISTER_STATUS_API, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegistryActivity.this, Constant.Message.SUCCESS_REGISTER_STATUS_API, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        hideLoading();

                        Toast.makeText(RegistryActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{

            }


        }

    }

    public boolean validateForm(){
        if(StringUtils.isNullOrEmpty(edUsername.getText().toString())){
            Toast.makeText(this, Constant.Message.USER_NAME_REQUIRE, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringUtils.isNullOrEmpty(edFullname.getText().toString())){
            Toast.makeText(this, Constant.Message.FULL_NAME_REQUIRE, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringUtils.isNullOrEmpty(edPassword.getText().toString())){
            Toast.makeText(this, Constant.Message.PASSWORD_REQUIRE, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringUtils.isNullOrEmpty(edEmail.getText().toString())){
            Toast.makeText(this, Constant.Message.EMAIL_REQUIRE, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringUtils.isNullOrEmpty(edPhone.getText().toString())){
            Toast.makeText(this, Constant.Message.PHONE_REQUIRE, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
