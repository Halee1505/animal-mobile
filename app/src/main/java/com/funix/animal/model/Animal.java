package com.funix.animal.model;



import android.graphics.Bitmap;



public class Animal {


    private final String photo; //ảnh động vật

    private final String photoBg; //ảnh background

    private final String name; //tên động vật

    private final String content; //nội dung mô tả động vật

    private boolean isFav; //động vật yêu thích

    private final String type;

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public boolean isFav() {
        return isFav;
    }

    public Animal(String type, String photo, String photoBg, String name, String content, boolean isFav) {
        this.type = type;
        this.photo = photo;
        this.photoBg = photoBg;
        this.name = name;
        this.isFav = isFav;
        this.content = content;
    }

    public String getType() {  return type; };

    public String getPhoto() {
        return photo;
    }

    public String getPhotoBg() {
        return photoBg;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
