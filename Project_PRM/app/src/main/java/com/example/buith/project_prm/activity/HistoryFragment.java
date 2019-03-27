package com.example.buith.project_prm.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ViewPagerAdapter;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.Account;
import com.example.buith.project_prm.model.OnLoadProductUser;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.model.ProductUserResponse;
import com.example.buith.project_prm.network.RetrofitInstance;
import com.example.buith.project_prm.service.ApiClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends BaseFragment implements OnLoadProductUser {
    View historyView;
    private RecyclerView.LayoutManager layoutManager;

    private HomeActivity home;

    private List<Product> listProduct;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        home = (HomeActivity) context;
    }

    public HistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        historyView = inflater.inflate(R.layout.history_fragment, container, false);

        loadProductUser(this);


        return historyView;
    }

    public void loadProductUser(OnLoadProductUser onLoadProductUser){
        SharedPreferences pref = historyView.getContext().getSharedPreferences(Constant.KeySharedPreference.USER_LOGIN, 0);
        String accJson = pref.getString(Constant.KeySharedPreference.USER_KEY_LOGIN, null);
        Gson json = new Gson();
        Account ac = json.fromJson(accJson, Account.class);
        if(ac == null){
            return;
        }
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(historyView.getContext());

        Call<ProductUserResponse> call = apiClient.getProductUser(ac.getUsername());
        if(home.isNetworkAvailable()){
            home.showLoading();
            call.enqueue(new Callback<ProductUserResponse>() {
                @Override
                public void onResponse(Call<ProductUserResponse> call, Response<ProductUserResponse> response) {
                    home.hideLoading();
                    ProductUserResponse productUserResponse = response.body();
                    if(productUserResponse != null){
                        if(productUserResponse.getStatus() == 1){
                            List<Product> list = productUserResponse.getList();
                            onLoadProductUser.onLoadProductUser(list);
                        }else{
                            Toast.makeText(historyView.getContext(), Constant.API.STATUS_API_ERROR, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(historyView.getContext(), Constant.API.RESPONSE_EMPTY, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ProductUserResponse> call, Throwable t) {

                }
            });
        }
    }

    public void initLayout(List<Product> list){
        TabLayout tabLayout = historyView.findViewById(R.id.nested_tabview);
        ViewPager viewPager = historyView.findViewById(R.id.nested_viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        OnSellFragement onSellFragement = new OnSellFragement();
//        onSellFragement.setList(list);
        adapter.addFragment(onSellFragement);
        adapter.addFragment(new SellOutdateFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab onsell = tabLayout.getTabAt(0);
        onsell.setText("Đang bán");
        TabLayout.Tab sellof = tabLayout.getTabAt(1);
        sellof.setText("Đã tạm dừng");

    }


    @Override
    public boolean onBackPressed() {
        return false;
    }


    @Override
    public void onLoadProductUser(List<Product> list) {
        listProduct = list;
        initLayout(list);
    }
}
