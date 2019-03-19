package com.example.buith.project_prm.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.buith.project_prm.R;

public class HomeActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout = findViewById(R.id.tabLayout_id);
        viewPager = findViewById(R.id.viewHome_id);
        initLayout(tabLayout, viewPager);
    }

}
