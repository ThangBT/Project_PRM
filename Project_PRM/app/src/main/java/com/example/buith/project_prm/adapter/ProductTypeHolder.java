package com.example.buith.project_prm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.ProductType;

import java.util.List;

public class ProductTypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView textView;
    private ImageView imageView;
    private FragmentCommunication mComminication;
    private List<ProductType> list;

    public ProductTypeHolder(@NonNull View itemView, FragmentCommunication communication) {
        super(itemView);
        textView = itemView.findViewById(R.id.product_type_name);
        imageView = itemView.findViewById(R.id.product_type_image);
        this.mComminication = communication;
        imageView.setOnClickListener(this);

    }

    public void setList(List<ProductType> list){
        this.list = list;
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

    @Override
    public void onClick(View v) {
        this.mComminication.onClickImage(getAdapterPosition(), list.get(getAdapterPosition()));
    }
}
