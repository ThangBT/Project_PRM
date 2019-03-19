package com.example.buith.project_prm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.buith.project_prm.model.FragmentCommunication;

import butterknife.ButterKnife;

public class BaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected T mData;
    protected FragmentCommunication mListener;

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
    }

    public void setListener(FragmentCommunication listener) {
        this.mListener = listener;
    }

    public void initData(T data) {
        mData = data;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null)
            mListener.onClickImage(getAdapterPosition(), mData);

    }

}
