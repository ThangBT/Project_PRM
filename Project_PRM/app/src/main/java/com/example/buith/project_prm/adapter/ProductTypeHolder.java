package com.example.buith.project_prm.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.ProductType;
import com.example.buith.project_prm.utils.ImageUtils;


import butterknife.BindView;

public class ProductTypeHolder extends BaseHolder<ProductType> {
    @BindView(R.id.product_type_name)
     TextView textView;
    @BindView(R.id.product_type_image)
     ImageView imageView;


    public ProductTypeHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void initData(ProductType data) {
        super.initData(data);
        textView.setText(data.getTypeName());
        String imageResize = ImageUtils.resizeBase64Image(data.getTypeImage());
        Bitmap image = ImageUtils.base64ToBitmap(data.getTypeImage());

        imageView.setImageBitmap(image);
    }

}
