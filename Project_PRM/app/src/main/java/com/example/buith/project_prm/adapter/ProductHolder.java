package com.example.buith.project_prm.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.Product;

import butterknife.BindView;

public class ProductHolder extends BaseHolder<Product> {
    @BindView(R.id.product_image)
    ImageView productImage;
    @BindView(R.id.product_name)
    TextView productName;
    @BindView(R.id.product_price)
    TextView productPrice;
    @BindView(R.id.address_sell)
    TextView addressSell;


    public ProductHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void initData(Product data) {
        super.initData(data);
        productName.setText(data.getProductName());
        productPrice.setText(data.getPrice().toString());
        addressSell.setText(data.getAdressSale());
    }
}
