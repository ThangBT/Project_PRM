package com.example.buith.project_prm.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.activity.AddSellProduct;
import com.example.buith.project_prm.constant.Constant;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    ArrayList<Uri> mArrayUri;
    Activity activity;
    ArrayList<String> listImg;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.ivGallery);
        }
    }

//    public MyRecyclerAdapter(Activity activity, ArrayList<Uri> mArrayUri) {
//        this.activity = activity;
//        this.mArrayUri = mArrayUri;
//    }

    public MyRecyclerAdapter(Activity activity, ArrayList<String> listImg) {
        this.activity = activity;
        this.listImg = listImg;
    }

    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // infalte the item Layout
        View v = activity.getLayoutInflater().inflate(R.layout.gv_item, null);
        MyRecyclerAdapter.MyViewHolder mvh = new MyRecyclerAdapter.MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        //myViewHolder.imageView.setImageURI(mArrayUri.get(position));
        byte[] decodedString = Base64.decode(listImg.get(position), Base64.DEFAULT);
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        myViewHolder.imageView.setImageBitmap(imageBitmap);
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), Constant.RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listImg != null) return listImg.size();
        return -1;
    }
}
