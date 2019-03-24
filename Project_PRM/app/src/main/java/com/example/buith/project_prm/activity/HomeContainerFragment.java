package com.example.buith.project_prm.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ViewPagerAdapter;
import com.example.buith.project_prm.constant.Constant;
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

public class HomeContainerFragment extends BaseFragment implements OnDataLoaded {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    HomeActivity home;
    View viewContainer;
    private List<ProductType> list;

    public HomeContainerFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        home = (HomeActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContainer = inflater.inflate(R.layout.fragment_home_container, container, false);
        loadProductType(this);
        return viewContainer;
    }

    // lay danh sach loai san pham tu api
    public void loadProductType(OnDataLoaded onDataLoaded){
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(viewContainer.getContext());
        Call<ProductTypeResponse> call = apiClient.getProductTypes();
        if(home.isNetworkAvailable()) {
            home.showLoading();
            call.enqueue(new Callback<ProductTypeResponse>() {
                @Override
                public void onResponse(Call<ProductTypeResponse> call, Response<ProductTypeResponse> response) {
                    home.hideLoading();
                    ProductTypeResponse response1 = response.body();
                    if (response1 == null) {
                        Toast.makeText(viewContainer.getContext(), "Không load được dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {
                        List<ProductType> list = response1.getListProductType();
                        onDataLoaded.ondataLoaded(list);
                    }
                }

                @Override
                public void onFailure(Call<ProductTypeResponse> call, Throwable t) {
                    home.hideLoading();

                    Toast.makeText(viewContainer.getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }else{

        }
    }


    public void initLayout(TabLayout tabLayout, ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        Bundle bundle = new Bundle();
        Gson json = new Gson();
        String jsonStr = json.toJson(list);
        bundle.putString(Constant.API.LIST_PRODUCT_TYPE, jsonStr);
        HomeFragment home = new HomeFragment();
        home.setArguments(bundle);
        adapter.addFragment(home);
        adapter.addFragment(new HistoryFragment());
        adapter.addFragment(new MenuFragment());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tabHome = tabLayout.getTabAt(0);
        tabHome.setIcon(R.drawable.home);
        TabLayout.Tab tabHis = tabLayout.getTabAt(1);
        tabHis.setIcon(R.drawable.document);
        TabLayout.Tab tabMenu = tabLayout.getTabAt(2);
        tabMenu.setIcon(R.drawable.menu);
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void ondataLoaded(List<ProductType> list) {
        this.list = list;
        tabLayout = viewContainer.findViewById(R.id.tabLayout_id);
        viewPager = viewContainer.findViewById(R.id.viewHome_id);
        initLayout(tabLayout, viewPager);
    }
}
