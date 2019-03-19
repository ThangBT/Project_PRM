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

public class ProductTypeAdapter extends BaseAdapter<ProductType> {
    private View item;
    private FragmentCommunication mCommunicator;

    public ProductTypeAdapter(Context context, List<ProductType> productTypeList) {
        super(context, productTypeList);

    }

    @Override
    public void setOnItemClickListener(FragmentCommunication listener) {
        this.mCommunicator = listener;
    }

    @Override
    protected BaseHolder onActualCreateHolder(@NonNull ViewGroup parent, int viewType) {
        item = LayoutInflater.from(mContext).inflate(R.layout.card_view_item, parent, false);
        ProductTypeHolder pth = new ProductTypeHolder(item);
        pth.setListener(mCommunicator);

        return pth;
    }

    @Override
    protected void onActualBindViewHolder(@NonNull BaseHolder<ProductType> holder, int position) {

    }



}


