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
import com.example.buith.project_prm.adapter.OnSellAdapter;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.Product;

import java.util.ArrayList;
import java.util.List;


public class SellOutdateFragment extends BaseFragment {

    private RecyclerView.LayoutManager layoutManager;

    private View view;

    public SellOutdateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sell_outdate_fragment, container, false);

        List<Product> list = new ArrayList<>();
//        list.add(new Product("Cuong", "Bat dong san", 200.0));
//        list.add(new Product("Cuong1", "Bat dong san", 200));
//        list.add(new Product("Cuong", "Bat dong san", 200));
//        list.add(new Product("Cuong1", "Bat dong san", 200));
//        list.add(new Product("Cuong", "Bat dong san", 200));
//        list.add(new Product("Cuong1", "Bat dong san", 200));
        RecyclerView recyclerView = view.findViewById(R.id.sell_off_recycleview);
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

        }
    };

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
