package com.example.buith.project_prm.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.buith.project_prm.R;

public class ProductsFragment extends Fragment {

    private View view;
    public ProductsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.products_fragment, container, false);
        TextView text = view.findViewById(R.id.textView2);
        text.setText("fgdgdgdgdfg");

        return view;
    }
}
