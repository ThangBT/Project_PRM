package com.example.buith.project_prm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.buith.project_prm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MomoHomeActivity extends AppCompatActivity {

    int environment = 1;//developer default - Production environment = 2
    @BindView(R.id.rdEnvironmentProduction)
    RadioButton rdEnvironmentProduction;
    @BindView(R.id.rdGroupEnvironment)
    RadioGroup rdGroupEnvironment;
    @BindView(R.id.btnPaymentMoMo)
    Button btnPaymentMoMo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_momo_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnPaymentMoMo)
    public void payment(View view){
        Bundle data = new Bundle();
        Intent intent = new Intent(MomoHomeActivity.this, PaymentActivity.class);
        data.putInt("key_environment", environment);
        intent.putExtras(data);
        startActivity(intent);
    }
}
