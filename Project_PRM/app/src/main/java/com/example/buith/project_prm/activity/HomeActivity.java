package com.example.buith.project_prm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.buith.project_prm.R;

import java.util.List;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flContainer, new HomeContainerFragment(),
                "homeFrag")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void replaceFragment(Fragment fragmentB) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null) {
                fragmentManager.beginTransaction().remove(fragment).commit();
            }
        }

        fragmentManager
                .beginTransaction()
                .replace(R.id.flContainer, fragmentB, "productFrag")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void createSellPost(View view){
        Intent intent = new Intent(this.getApplicationContext(), SellProductActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        boolean handled = false;
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null) {
                fragmentManager.beginTransaction().remove(fragment).commit();
            }
//            if (fragment instanceof BaseFragment) {
//                handled = ((BaseFragment)fragment).onBackPressed();
//
//                if (!handled) {
//                    break;
//                }
//            }
        }

//        if (!handled) {
//            super.onBackPressed();
//        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainer, new HomeContainerFragment(),"homeFrag")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
