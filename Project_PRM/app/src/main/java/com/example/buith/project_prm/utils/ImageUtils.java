package com.example.buith.project_prm.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.buith.project_prm.R;

import java.io.ByteArrayOutputStream;

public class ImageUtils {
    public static Bitmap base64ToBitmap(String imageBase64){
        if(imageBase64 == null){

            return null;
        }
        byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public static String resizeBase64Image(String base64image) {
        try {
            byte[] encodeByte = Base64.decode(base64image.getBytes(), Base64.DEFAULT);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
//            int size = Tools.dpToPx(100);
            Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length, options);
            if (image.getHeight() <= 100 && image.getWidth() <= 100) {
                return base64image;
            }
            image = Bitmap.createScaledBitmap(image, 100, 100, false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            System.gc();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        } catch (Exception e) {
            return null;
        }
    }
}
