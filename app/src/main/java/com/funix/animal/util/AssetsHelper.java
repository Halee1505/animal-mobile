package com.funix.animal.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AssetsHelper {

    public static final String ANIMAL_FOLDER = "animal/";
    public static final String ANIMAL_DESCRIPTION_FOLDER =  "description/";
    public static final String BIRD_FOLDER = "bird/";
    public static final String MAMMAL_FOLDER =  "mammal/";
    public static final String SEA_FOLDER =  "sea/";

    public ArrayList<String> getAnimalImages(Context context, String animalType) {
        ArrayList<String> animalList = new ArrayList<>();
        String animalFolder;
        switch (animalType) {
            case "bird":
                animalFolder =ANIMAL_FOLDER + BIRD_FOLDER;
                break;
            case "mammal":
                animalFolder = ANIMAL_FOLDER +MAMMAL_FOLDER;
                break;
            case "sea":
                animalFolder = ANIMAL_FOLDER + SEA_FOLDER;
                break;
            default:
                return animalList;
        }
        AssetManager assetManager = context.getAssets();
        try {
            String[] files = assetManager.list(animalFolder);
            if (files != null) {
                for (String file : files) {
                    animalList.add(animalFolder + file);
                }
            }
        } catch (IOException e) {
            Log.e("AssetsHelper", "Error reading asset files", e);
        }

        return animalList;
    }

    public static String getNameFromPath(String path){
        String[] parts = path.split("/");
        if(parts.length == 0)  return "";
        return parts[parts.length - 1].split("\\.")[0].split("_")[1];
    }

    public static String getContent(Context context, String type, String name){
        String content = "";
        String animalFolder;
        switch (type) {
            case "bird":
                animalFolder =ANIMAL_DESCRIPTION_FOLDER + BIRD_FOLDER;
                break;
            case "mammal":
                animalFolder = ANIMAL_DESCRIPTION_FOLDER +MAMMAL_FOLDER;
                break;
            case "sea":
                animalFolder = ANIMAL_DESCRIPTION_FOLDER + SEA_FOLDER;
                break;
            default:
                return content;
        }
        AssetManager assetManager = context.getAssets();
        try {
            String[] files = assetManager.list(animalFolder);
            if (files != null) {
                for (String file : files) {
                    if(file.contains(name)){
                        InputStream inputStream = assetManager.open(animalFolder + file);
                        byte[] buffer = new byte[inputStream.available()];
                        inputStream.read(buffer);
                        inputStream.close();
                        content = new String(buffer);
                        return content;
                    }
                }
            }
        } catch (IOException e) {
            Log.e("AssetsHelper", "Error reading asset files", e);
        }
        return content;

    }

    public Bitmap getBitmapFromPath(Context context, String path) {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            // First decode with inJustDecodeBounds=true to check dimensions
            inputStream = assetManager.open(path);
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, 50, 50); // Adjust the target width and height as needed

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565; // Use RGB_565 to reduce memory usage
            inputStream = assetManager.open(path);
            Bitmap resizedBitmap = BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();

            return resizedBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return null;
        }
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
