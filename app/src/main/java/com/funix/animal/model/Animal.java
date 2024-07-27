package com.funix.animal.model;



import android.graphics.Bitmap;

import java.io.Serializable;


public class Animal implements Serializable {


    private final String photo; //ảnh động vật

    private final String photoBg; //ảnh background

    private final String name; //tên động vật

    private final String content; //nội dung mô tả động vật

    private boolean isFav; //động vật yêu thích

    private String phone;

    private final String type;



    public Animal(String type, String photo, String photoBg, String name, String content, boolean isFav, String phone) {
        this.type = type;
        this.photo = photo;
        this.photoBg = photoBg;
        this.name = name;
        this.isFav = isFav;
        this.content = content;
        this.phone = phone;
    }
    // Constructor without phone parameter
    public Animal(String type, String photo, String photoBg, String name, String content, boolean isFav) {
        this(type, photo, photoBg, name, content, isFav, "");
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
    public String getPhone() { return phone;}
    public void setPhone(String nPhone ) { phone = nPhone;}
    public void setFav(boolean fav) {
        isFav = fav;
    }

    public boolean isFav() {
        return isFav;
    }
}
