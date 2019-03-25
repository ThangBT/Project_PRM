package com.example.buith.project_prm.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.buith.project_prm.R;

public class ImageUtils {
    public static Bitmap base64ToBitmap(String imageBase64){
        if(imageBase64 == null){

            return null;
        }
        byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
