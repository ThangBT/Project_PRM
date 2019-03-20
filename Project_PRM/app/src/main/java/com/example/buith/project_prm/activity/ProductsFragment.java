package com.example.buith.project_prm.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buith.project_prm.R;

public class ProductsFragment extends BaseFragment {

    private final static String TAG = "productFragment";

    private View view;
    public ProductsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.products_fragment, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setOnClickListener(v -> {
            HomeActivity homeActivity = (HomeActivity) getActivity();
            if (homeActivity != null) {
                homeActivity.replaceFragment(new HomeContainerFragment());
            }
        });

        return view;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
