package com.example.buith.project_prm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.ProductType;

import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeHolder> {
    private LayoutInflater layoutInflater;
    private List<ProductType> productTypeList ;
    private ImageView imageView;
    private View item;
    private FragmentCommunication mCommunicator;

    public ProductTypeAdapter(Context context, List<ProductType> productTypeList, FragmentCommunication communication) {
        this.productTypeList = productTypeList;
        this.layoutInflater = LayoutInflater.from(context);
        this.mCommunicator = communication;

    }

    @NonNull
    @Override
    public ProductTypeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        item = layoutInflater.inflate(R.layout.card_view_item, viewGroup, false);
        imageView = item.findViewById(R.id.product_type_image);


        ProductTypeHolder pth = new ProductTypeHolder(item, mCommunicator);
        return pth;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductTypeHolder productTypeHolder, int i) {
        final int position = i;
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


