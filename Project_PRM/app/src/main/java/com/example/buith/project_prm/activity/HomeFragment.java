package com.example.buith.project_prm.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ProductTypeAdapter;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.ProductType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class HomeFragment extends BaseFragment {
    View homeView;
    private RecyclerView.LayoutManager layoutManager;
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = getView() != null ? getView() : inflater.inflate(R.layout.home_fragment, container, false);
        RecyclerView recyclerView = homeView.findViewById(R.id.my_recycler_view);
        String listjson = getArguments().getString(Constant.API.LIST_PRODUCT_TYPE);
        Gson json = new Gson();
        List<ProductType> list = json.fromJson(listjson, new TypeToken<List<ProductType>>(){}.getType());
        ProductTypeAdapter adapter = new ProductTypeAdapter(this.getContext(),list);
        adapter.setOnItemClickListener(communication);

        layoutManager = new GridLayoutManager(homeView.getContext(), 2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return homeView;
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
}
