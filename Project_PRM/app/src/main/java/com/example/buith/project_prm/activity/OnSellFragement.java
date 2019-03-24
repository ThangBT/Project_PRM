package com.example.buith.project_prm.activity;

import android.content.Intent;
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
import com.example.buith.project_prm.adapter.OnSellAdapter;
import com.example.buith.project_prm.adapter.ProductTypeAdapter;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OnSellFragement extends BaseFragment {

    private View view;

    private RecyclerView.LayoutManager layoutManager;

    public OnSellFragement() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.on_sell_fragment, container, false);

        List<Product> list = new ArrayList<>();
//        list.add(new Product("Cuong", "Bat dong san", 200));
//        list.add(new Product("Cuong1", "Bat dong san", 200));
//        list.add(new Product("Cuong", "Bat dong san", 200));
//        list.add(new Product("Cuong1", "Bat dong san", 200));
//        list.add(new Product("Cuong", "Bat dong san", 200));
//        list.add(new Product("Cuong1", "Bat dong san", 200));
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
