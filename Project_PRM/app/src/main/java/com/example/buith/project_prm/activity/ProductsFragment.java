package com.example.buith.project_prm.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ProductListAdapter;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends BaseFragment {

    private final static String TAG = "productFragment";

    private View view;

    RecyclerView.LayoutManager layoutManager;

    public ProductsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.products_fragment, container, false);
        List<Product> list = new ArrayList<>();
        list.add(new Product("Cuong",  200, "Ha noi"));
        list.add(new Product("Cuong",  200, "Ha noi"));
        list.add(new Product("Cuong",  200, "Ha noi"));
        list.add(new Product("Cuong",  200, "Ha noi"));
        list.add(new Product("Cuong",  200, "Ha noi"));

        RecyclerView recyclerView = view.findViewById(R.id.recycle_list_product);
        ProductListAdapter adapter = new ProductListAdapter(this.getContext(), list);
        adapter.setOnItemClickListener(communication);
        layoutManager = new LinearLayoutManager(this.getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // set action for back arrow
        ImageView imageView = view.findViewById(R.id.back_arrow);
        imageView.setOnClickListener(v -> {
            HomeActivity homeActivity = (HomeActivity) getActivity();
            if (homeActivity != null) {
                homeActivity.replaceFragment(new HomeContainerFragment(), null);
            }
        });

        return view;
    }

    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void onClickImage(int position, Object args) {
            HomeActivity homeActivity = (HomeActivity) getActivity();
            homeActivity.moveToActivity((Product) args);
        }
    };

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
