package com.example.buith.project_prm.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ProductTypeAdapter;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    View historyView;
    private RecyclerView.LayoutManager layoutManager;
    public HistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        historyView = inflater.inflate(R.layout.history_fragment, container, false);
        TabLayout tabLayout = historyView.findViewById(R.id.nested_tabview);
        ViewPager viewPager = historyView.findViewById(R.id.nested_viewpager);
        initLayout(tabLayout, viewPager);

        return historyView;
    }

    public void initLayout(TabLayout tabLayout, ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new OnSellFragement());
        adapter.addFragment(new SellOutdateFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab onsell = tabLayout.getTabAt(0);
        onsell.setText("Đang bán");
        TabLayout.Tab sellof = tabLayout.getTabAt(1);
        sellof.setText("Đã tạm dừng");

    }


}
