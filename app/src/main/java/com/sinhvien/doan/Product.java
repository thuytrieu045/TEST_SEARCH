package com.sinhvien.doan;

public class Product {
    private int id;
    private String name,imgURL;
    private String description;
    private String avatar;
    private int imageResource; // Thêm biến hình ảnh

    // Constructor đầy đủ
    public Product(int id, String name, String description, String avatar, int imageResource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.imageResource = imageResource;
    }

    // Constructor đơn giản hơn
   /* public Product(int id, String name, String description, int imageResource) {
        this.id = String.valueOf(id);
        this.name = name;
        this.description = description;
        this.avatar = ""; // Nếu không có ảnh URL
        this.imageResource = imageResource;
    }*/

    //Constructor tạm thời


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}