package com.example.buith.project_prm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.activity.HomeActivity;
import com.example.buith.project_prm.activity.ProductsFragment;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.ProductType;

import java.util.List;

import butterknife.BindView;

public class ProductTypeHolder extends BaseHolder<ProductType> {
    @BindView(R.id.product_type_name)
     TextView textView;
    @BindView(R.id.product_type_image)
     ImageView imageView;


    public ProductTypeHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.product_type_image);
        imageView.setOnClickListener(this);
    }

    @Override
    public void initData(ProductType data) {
        super.initData(data);
        textView.setText(data.getTypeName());
    }

    @Override
    public void onClick(View view) {

    }
}
