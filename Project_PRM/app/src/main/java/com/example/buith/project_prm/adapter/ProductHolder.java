package com.example.buith.project_prm.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.activity.MomoHomeActivity;
import com.example.buith.project_prm.activity.RegistryActivity;
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
    @BindView(R.id.icon_top_up)
    ImageView topImage;


    public ProductHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void initData(Product data) {
        super.initData(data);
        productName.setText(data.getProductName());
        productPrice.setText(data.getPrice().toString());
        addressSell.setText(data.getAdressSale());
        topImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(itemView.getContext())
                        .setTitle("Bạn có muốn đẩy top bài viết!")
                        .setMessage("")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(itemView.getContext(), MomoHomeActivity.class);
                                itemView.getContext().startActivity(intent);
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });
    }
}
