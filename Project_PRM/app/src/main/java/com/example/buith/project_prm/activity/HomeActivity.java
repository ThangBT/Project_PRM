package com.example.buith.project_prm.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.buith.project_prm.R;

public class HomeActivity extends BaseActivity {

//    private TabLayout tabLayout;
//    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        tabLayout = findViewById(R.id.tabLayout_id);
//        viewPager = findViewById(R.id.viewHome_id);
//        initLayout(tabLayout, viewPager);
        getSupportFragmentManager().beginTransaction().add(R.id.flContainer, new HomeContainerFragment(),
                HomeContainerFragment.class.getSimpleName()).commit();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment, "A").commit();
    }
}
