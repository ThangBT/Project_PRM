package com.example.buith.project_prm.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ViewPagerAdapter;

public class HomeContainerFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_container, container, false);

        tabLayout = view.findViewById(R.id.tabLayout_id);
        viewPager = view.findViewById(R.id.viewHome_id);

        initLayout(tabLayout, viewPager);

        return view;
    }

    public void initLayout(TabLayout tabLayout, ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new HistoryFragment());
        adapter.addFragment(new MenuFragment());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tabHome = tabLayout.getTabAt(0);
        tabHome.setIcon(R.drawable.home);
        TabLayout.Tab tabHis = tabLayout.getTabAt(1);
        tabHis.setIcon(R.drawable.document);
        TabLayout.Tab tabMenu = tabLayout.getTabAt(2);
        tabMenu.setIcon(R.drawable.menu);
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }
}
