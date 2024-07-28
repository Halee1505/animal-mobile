package com.funix.animal.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.funix.animal.R;

public class ToastUtil {

    public static void showCustomToast(Context context, String imageUrl) {
        // Inflate custom toast layout
        AssetsHelper assetsHelper = new AssetsHelper();
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast, null);
        System.out.println(imageUrl);
        // Set the icon and text in the custom toast layout
        ImageView icon = layout.findViewById(R.id.toast_image);
        Bitmap bitmap = assetsHelper.getBitmapFromPath(context, imageUrl);
        icon.setImageBitmap(bitmap);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}