package com.example.buith.project_prm.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.Account;
import com.example.buith.project_prm.model.Token;
import com.example.buith.project_prm.service.ApiClient;
import com.example.buith.project_prm.network.RetrofitInstance;
import com.example.buith.project_prm.utils.Define;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private static MainActivity mainActivity;
    private Button btnFacebook;
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> loginResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnFacebook = (LoginButton) findViewById(R.id.btnFacebook);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        callbackManager = CallbackManager.Factory.create();
//        mainActivity = this;
//        initFaceBook();
//        LoginManager.getInstance().registerCallback(callbackManager, loginResult);
//        btnFacebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginFaceBook();
//            }
//        });
//        printKeyHash(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void login(View view){
        EditText username = findViewById(R.id.etUsername);
        EditText password = findViewById(R.id.etPassword);
        Map<String,String> map = new HashMap<>();
        map.put("username", username.getText().toString());
        map.put("password", password.getText().toString());
        map.put("grant_type", "password");
        ApiClient loginService = RetrofitInstance.getRetrofitInstance(this);
        Call<Token> call = loginService.login(map);
        if(isNetworkAvailable()){
            showLoading();
            call.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    Token token = response.body();
                    hideLoading();
                    if(token != null){
                        Account ac = token.getAccount();
                        SharedPreferences pref = getSharedPreferences(Constant.KeySharedPreference.USER_LOGIN, MODE_PRIVATE);
                        SharedPreferences.Editor prefEdit = pref.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(ac);
                        prefEdit.putString(Constant.KeySharedPreference.USER_KEY_LOGIN, json);
                        prefEdit.putString(Constant.KeySharedPreference.ACCESS_TOKEN, token.getAccess_token());
                        prefEdit.commit();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, Constant.Message.ERROR_LOGIN_RESPONSE_API, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    hideLoading();
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            hideLoading();
            Toast.makeText(MainActivity.this, Define.NO_INTERNET, Toast.LENGTH_SHORT).show();
        }


    }

    //Login facebook with permisstion
    public void loginFaceBook() {
        LoginManager.getInstance().logInWithReadPermissions(mainActivity, Arrays.asList("public_profile", "user_friends", "email"));
    }

    //Hàm check login facebook
    public boolean isLoggedInFaceBook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    //Lấy Avatar
    public URL extractFacebookIcon(String id) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageURL = new URL("http://graph.facebook.com/" + id
                    + "/picture?type=large");
            return imageURL;
        } catch (Throwable e) {
            return null;
        }
    }

    public void initFaceBook() {
        loginResult = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Login thành công xử lý tại đây
                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Application code
                                String name = object.optString(getString(R.string.name));
                                String id = object.optString(getString(R.string.id));
                                String email = object.optString(getString(R.string.email));
                                String link = object.optString(getString(R.string.link));
                                URL imageURL = extractFacebookIcon(id);
                                Log.d("name: ", name);
                                Log.d("id: ", id);
                                Log.d("email: ", email);
                                Log.d("link: ", link);
                                Log.d("imageURL: ", imageURL.toString());
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString(getString(R.string.fields), getString(R.string.fields_name));
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };
    }

    //Get keyHash
    public String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    public void registryAccount(View view) {
        Intent intent = new Intent(getApplicationContext(), RegistryActivity.class);
        startActivity(intent);
    }


}
