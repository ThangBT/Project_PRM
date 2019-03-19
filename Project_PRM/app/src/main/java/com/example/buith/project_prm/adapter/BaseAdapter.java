package com.example.buith.project_prm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.buith.project_prm.model.FragmentCommunication;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {

    protected List<T> mListItems = new ArrayList<>();
    protected Context mContext;
    protected FragmentCommunication mListener;

    public BaseAdapter(Context context, List<T> listItems) {
        mContext = context;
        if (listItems != null)
            mListItems = listItems;
    }

    public void setOnItemClickListener(FragmentCommunication listener) {
        mListener = listener;
    }

    protected abstract BaseHolder onActualCreateHolder(@NonNull ViewGroup parent, int viewType);

    protected abstract void onActualBindViewHolder(@NonNull BaseHolder<T> holder, int position);

    @NonNull
    @Override
    public BaseHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return onActualCreateHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder<T> holder, int position) {
        T data = mListItems.get(position);
        holder.initData(data);
        holder.setListener(mListener);
        onActualBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
