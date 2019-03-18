package com.example.buith.project_prm.activity;

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

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ProductTypeAdapter;
import com.example.buith.project_prm.model.ProductType;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    View homeView;
    private RecyclerView.LayoutManager layoutManager;
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.home_fragment, container, false);
        RecyclerView recyclerView = homeView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        List<ProductType> list = new ArrayList<>();
        list.add(new ProductType("", "Thực phẩm"));
        list.add(new ProductType("", "Bất động sản"));
        list.add(new ProductType("", "Ô tô, xe máy"));
        list.add(new ProductType("", "Làm đẹp"));
        list.add(new ProductType("", "Ngoại thất, đồ gia dụng"));
        list.add(new ProductType("", "Đồ điện tử"));
        list.add(new ProductType("", "Thú cưng"));
        ProductTypeAdapter adapter = new ProductTypeAdapter(list);
        layoutManager = new LinearLayoutManager(homeView.getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return homeView;
    }
}
