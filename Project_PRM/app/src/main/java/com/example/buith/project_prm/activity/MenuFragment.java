package com.example.buith.project_prm.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.Account;
import com.example.buith.project_prm.utils.ImageUtils;
import com.google.gson.Gson;

public class MenuFragment extends BaseFragment {
    View menu;

    public MenuFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        menu = inflater.inflate(R.layout.menu_fragment, container, false);
        SharedPreferences pref = menu.getContext().getSharedPreferences(Constant.KeySharedPreference.USER_LOGIN, Context.MODE_PRIVATE);
        Button login = menu.findViewById(R.id.login_menu);
        Button logout = menu.findViewById(R.id.logout_menu);
        ImageView image = menu.findViewById(R.id.avatar);
        if(pref.getString(Constant.KeySharedPreference.USER_KEY_LOGIN, null) == null){
            login.setText("Đăng nhập/Đăng ký");
            image.setImageResource(R.drawable.user);
            logout.setVisibility(menu.GONE);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(menu.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            Gson gson = new Gson();
            Account ac = gson.fromJson(pref.getString(Constant.KeySharedPreference.USER_KEY_LOGIN, null), Account.class);
            login.setText(ac.getFullname());

            image.setImageBitmap(ImageUtils.base64ToBitmap(ac.getAvatar()));
            logout.setVisibility(menu.VISIBLE);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences pref = menu.getContext().getSharedPreferences(Constant.KeySharedPreference.USER_LOGIN, Context.MODE_PRIVATE);
                    pref.edit().remove(Constant.KeySharedPreference.USER_KEY_LOGIN).commit();
                    Intent intent = new Intent(menu.getContext(), MainActivity.class);
                    startActivity(intent);

                }
            });
        }

        return menu;

    }


    @Override
    public boolean onBackPressed() {
        return false;
    }
}
