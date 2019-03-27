package com.example.buith.project_prm.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.OnSellAdapter;
import com.example.buith.project_prm.adapter.ProductTypeAdapter;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.Account;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.model.ProductResponse;
import com.example.buith.project_prm.network.RetrofitInstance;
import com.example.buith.project_prm.service.ApiClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSellFragement extends BaseFragment {

    private View view;

    private RecyclerView.LayoutManager layoutManager;

    private HomeActivity home;

    private List<Product> list;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        home = (HomeActivity) context;
    }

    public OnSellFragement() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.on_sell_fragment, container, false);
        List<Product> list = new ArrayList<>();
        getProduct();
        return view;
    }

    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void onClickImage(int position, Object args) {
            Intent intent = new Intent(view.getContext(), UpdateProductActivity.class);
            intent.putExtra("product", (Product)args);
            Bundle bundle = new Bundle();
            startActivity(intent);
        }
    };

    @Override
    public boolean onBackPressed() {
        return false;
    }
    public void getProduct(){
        SharedPreferences pref = getActivity().getSharedPreferences(Constant.KeySharedPreference.USER_LOGIN, Context.MODE_PRIVATE);
        String acc = pref.getString(Constant.KeySharedPreference.USER_KEY_LOGIN, null);
        Gson gson = new Gson();
        Account account = gson.fromJson(acc, Account.class);
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(view.getContext());
        Call<ProductResponse> call = apiClient.getProductActiveByUserName(account.getUsername());
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse response1 = response.body();
                if (response1 == null) {
                    Toast.makeText(view.getContext(), "Không load được dữ liệu loại địa chỉ", Toast.LENGTH_SHORT).show();
                } else {
                    List<Product> list = response1.getProductList();
                    loadProduct(list);
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(view.getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadProduct(List<Product> list) {
        if(list.isEmpty()){
            list = new ArrayList<>();
        }
        RecyclerView recyclerView = view.findViewById(R.id.recycleview_on_sell);
        OnSellAdapter adapter = new OnSellAdapter(this.getContext(), list);
        adapter.setOnItemClickListener(communication);
        layoutManager = new LinearLayoutManager(this.getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
