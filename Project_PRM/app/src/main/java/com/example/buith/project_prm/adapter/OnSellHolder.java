package com.example.buith.project_prm.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.utils.ImageUtils;
import com.example.buith.project_prm.utils.StringUtils;

import butterknife.BindView;

public class OnSellHolder extends BaseHolder<Product> {
    @BindView(R.id.productName)
    TextView productName;
    @BindView(R.id.type)
    TextView typeId;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.image_on_sell)
    ImageView image;

    public OnSellHolder(@NonNull View itemView) {
        super(itemView);

    }

    @Override
    public void initData(Product data) {
        super.initData(data);
        productName.setText(data.getProductName());
        typeId.setText(data.getProductName());
        price.setText(StringUtils.formatMoney(data.getPrice()));
        image.setImageBitmap(ImageUtils.base64ToBitmap(data.getImage()));
    }
}
