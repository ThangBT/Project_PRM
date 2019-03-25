package com.example.buith.project_prm.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.Image;
import com.example.buith.project_prm.utils.ImageUtils;

import butterknife.BindView;

public class ImageHolder extends BaseHolder<Image> {

    @BindView(R.id.image_product_detail)
    ImageView imageView;

    public ImageHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void initData(Image data) {
        super.initData(data);
        imageView.setImageBitmap(ImageUtils.base64ToBitmap(data.getImage()));
    }
}
