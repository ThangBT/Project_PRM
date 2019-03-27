package com.example.buith.project_prm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.model.ProductType;
import com.google.gson.Gson;


public class HomeActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flContainer, new HomeContainerFragment(),
                        "homeFrag")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

    }


    public void replaceFragment(Fragment fragmentB, Object args) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        if(args != null){
            Toast.makeText(this.getApplicationContext(), ((ProductType) args).getTypeName(), Toast.LENGTH_SHORT).show();
            if(args instanceof ProductType) {
                bundle.putInt(Constant.PRODUCT_TYPE_ID, ((ProductType) args).getTypeId());
                fragmentB.setArguments(bundle);
            }
        }

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

    public void moveToActivity(Product product){
//        Toast.makeText(this.getApplicationContext(), product.getProductName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SellProductActivity.class);

        intent.putExtra(Constant.Intent.PRODUCT_KEY, product);
        startActivity(intent);
    }

    public void createSellPost(View view) {
        Intent intent = new Intent(this, AddSellProduct.class);
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

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer, new HomeContainerFragment(), "homeFrag")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }
}
