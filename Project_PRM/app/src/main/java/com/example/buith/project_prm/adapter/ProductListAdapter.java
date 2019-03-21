package com.example.buith.project_prm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.Product;

import java.util.List;

public class ProductListAdapter extends BaseAdapter<Product> {

    private View item;

    public ProductListAdapter(Context context, List<Product> listItems) {
        super(context, listItems);
    }

    @Override
    protected BaseHolder onActualCreateHolder(@NonNull ViewGroup parent, int viewType) {
        item = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false);
        ProductHolder holder = new ProductHolder(item);
        return holder;
    }

    @Override
    protected void onActualBindViewHolder(@NonNull BaseHolder<Product> holder, int position) {

    }
}
