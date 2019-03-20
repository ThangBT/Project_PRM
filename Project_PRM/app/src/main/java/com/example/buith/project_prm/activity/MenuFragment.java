package com.example.buith.project_prm.activity;

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

import com.example.buith.project_prm.R;

public class MenuFragment extends BaseFragment {
    View menu;

    public MenuFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        menu = inflater.inflate(R.layout.menu_fragment, container, false);
        SharedPreferences preferences = this.getActivity().getSharedPreferences("account", 0);
        Button login = menu.findViewById(R.id.login_menu);
        Button logout = menu.findViewById(R.id.logout_menu);
        if(preferences.getString("account", null) == null){
            login.setText("Đăng nhập/Đăng ký");
            logout.setVisibility(menu.GONE);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
