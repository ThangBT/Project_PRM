package com.example.buith.project_prm.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.Product;

import butterknife.BindView;

public class OnSellHolder extends BaseHolder<Product> {
    @BindView(R.id.productName)
    TextView productName;
    @BindView(R.id.type)
    TextView typeId;
    @BindView(R.id.price)
    TextView price;

    public OnSellHolder(@NonNull View itemView) {
        super(itemView);

    }

    @Override
    public void initData(Product data) {
        super.initData(data);
        productName.setText(data.getProductName());
        typeId.setText(data.getTypeId());
        price.setText(data.getPrice().toString());
    }
}
