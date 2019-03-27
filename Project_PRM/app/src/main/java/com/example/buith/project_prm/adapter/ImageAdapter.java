package com.example.buith.project_prm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.model.Image;

import java.util.List;

public class ImageAdapter extends BaseAdapter<Image> {

    private View item;

    public ImageAdapter(Context context, List<Image> listItems) {
        super(context, listItems);
    }

    @Override
    protected BaseHolder onActualCreateHolder(@NonNull ViewGroup parent, int viewType) {
        item = LayoutInflater.from(mContext).inflate(R.layout.list_image_item, parent, false);
        ImageHolder holder = new ImageHolder(item);
        return holder;
    }

    @Override
    protected void onActualBindViewHolder(@NonNull BaseHolder<Image> holder, int position) {

    }
}
