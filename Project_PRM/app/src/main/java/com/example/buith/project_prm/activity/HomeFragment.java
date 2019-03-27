package com.example.buith.project_prm.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ProductTypeAdapter;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.OnDataLoaded;
import com.example.buith.project_prm.model.ProductType;
import com.example.buith.project_prm.model.ProductTypeResponse;
import com.example.buith.project_prm.network.RetrofitInstance;
import com.example.buith.project_prm.service.ApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment implements OnDataLoaded {
    View homeView;

    HomeActivity home;

    private RecyclerView.LayoutManager layoutManager;
    public HomeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        home = (HomeActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = getView() != null ? getView() : inflater.inflate(R.layout.home_fragment, container, false);
        loadProductType(this);

        return homeView;
    }

    // lay danh sach loai san pham tu api
    public void loadProductType(OnDataLoaded onDataLoaded){
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(homeView.getContext());
        Call<ProductTypeResponse> call = apiClient.getProductTypes();
        if(home.isNetworkAvailable()) {
            home.showLoading();
            call.enqueue(new Callback<ProductTypeResponse>() {
                @Override
                public void onResponse(Call<ProductTypeResponse> call, Response<ProductTypeResponse> response) {
                    home.hideLoading();
                    ProductTypeResponse response1 = response.body();
                    if (response1 == null) {
                        Toast.makeText(homeView.getContext(), "Không load được dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {
                        List<ProductType> list = response1.getListProductType();
                        onDataLoaded.ondataLoaded(list);
                    }
                }

                @Override
                public void onFailure(Call<ProductTypeResponse> call, Throwable t) {
                    home.hideLoading();

                    Toast.makeText(homeView.getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }else{

        }
    }

    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void onClickImage(int position, Object args) {
            HomeActivity homeActivity = (HomeActivity) getActivity();
            if (homeActivity != null) {
                homeActivity.replaceFragment(new ProductsFragment(), args);
            }
        }
    };

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void ondataLoaded(List<ProductType> list) {
        RecyclerView recyclerView = homeView.findViewById(R.id.my_recycler_view);
        ProductTypeAdapter adapter = new ProductTypeAdapter(this.getContext(),list);
        adapter.setOnItemClickListener(communication);

        layoutManager = new GridLayoutManager(homeView.getContext(), 2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
