package com.example.buith.project_prm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeHolder> {
    private List<ProductType> productTypeList ;

    public ProductTypeAdapter(List<ProductType> productTypeList) {
        this.productTypeList = productTypeList;
    }

    @NonNull
    @Override
    public ProductTypeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View item = layoutInflater.inflate(R.layout.card_view_item, viewGroup, false);
        TextView textView = item.findViewById(R.id.product_type_name);
        ImageView imageView = item.findViewById(R.id.product_type_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(item.getContext(), "hkshfsj", Toast.LENGTH_SHORT).show();
            }
        });
        ProductTypeHolder pth = new ProductTypeHolder(item);
        return pth;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductTypeHolder productTypeHolder, int i) {
        if(productTypeList != null){
            productTypeHolder.getTextView().setText(productTypeList.get(i).getTypeName());

        }
    }

    @Override
    public int getItemCount() {

        if(productTypeList != null){
            return productTypeList.size();
        }
        return 0;
    }
}
