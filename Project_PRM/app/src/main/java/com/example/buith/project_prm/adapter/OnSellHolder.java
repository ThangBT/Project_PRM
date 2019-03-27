package com.example.buith.project_prm.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
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
    @BindView(R.id.image_on_sell)
    ImageView imageView;

    public OnSellHolder(@NonNull View itemView) {
        super(itemView);

    }

    @Override
    public void initData(Product data) {
        super.initData(data);
        productName.setText(data.getProductName());
        typeId.setText(String.valueOf(data.getDescription()));
        price.setText(data.getPrice().toString());
        byte[] decodedString = Base64.decode(data.getImage(), Base64.DEFAULT);
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(imageBitmap);
    }
}
