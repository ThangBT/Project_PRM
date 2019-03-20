package com.example.buith.project_prm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.Product;

import java.util.List;

public class OnSellAdapter extends BaseAdapter<Product> {

    private View item;

    public OnSellAdapter(Context context, List<Product> listItems) {
        super(context, listItems);
    }

    @Override
    protected BaseHolder onActualCreateHolder(@NonNull ViewGroup parent, int viewType) {
        item = LayoutInflater.from(mContext).inflate(R.layout.on_sell_item, parent, false);

        return new OnSellHolder(item);
    }

    @Override
    protected void onActualBindViewHolder(@NonNull BaseHolder<Product> holder, int position) {

    }
}
