package com.example.buith.project_prm.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.buith.project_prm.R;

public class HomeActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initLayout();
    }

    public void initLayout(){
        tabLayout = findViewById(R.id.tabLayout_id);
        viewPager = findViewById(R.id.viewHome_id);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new HistoryFragment());
        adapter.addFragment(new MenuFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tabHome = tabLayout.getTabAt(0);
        tabHome.setIcon(R.drawable.home);
        TabLayout.Tab tabHis = tabLayout.getTabAt(1);
        tabHis.setIcon(R.drawable.document);
        TabLayout.Tab tabMenu = tabLayout.getTabAt(2);
        tabMenu.setIcon(R.drawable.menu);

    }
}
