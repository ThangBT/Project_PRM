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
import com.example.buith.project_prm.model.OnLoadProductUser;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.model.ProductUserResponse;
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

    public void setList(List<Product> list){
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.on_sell_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycleview_on_sell);
        OnSellAdapter adapter = new OnSellAdapter(this.getContext(),list);
        adapter.setOnItemClickListener(communication);
        layoutManager = new LinearLayoutManager(this.getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }



    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void onClickImage(int position, Object args) {
            Intent intent = new Intent(view.getContext(), AddSellProduct.class);
            Bundle bundle = new Bundle();
            startActivity(intent);
        }
    };

    @Override
    public boolean onBackPressed() {
        return false;
    }


}
