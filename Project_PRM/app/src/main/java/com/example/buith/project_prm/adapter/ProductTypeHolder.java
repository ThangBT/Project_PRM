package com.example.buith.project_prm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buith.project_prm.R;

public class ProductTypeHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageView imageView;
    public ProductTypeHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.product_type_name);
        imageView = itemView.findViewById(R.id.product_type_image);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
