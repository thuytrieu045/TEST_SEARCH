package com.sinhvien.doan;

public class Product {
    private String id;
    private String name;
    private String description;
    private String avatar;
    private double price;  // Thêm biến price
    private int imageResource; // Thêm biến hình ảnh

    // Constructor đầy đủ
    public Product(String id, String name, String description, String avatar, double price, int imageResource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.price = price;
        this.imageResource = imageResource;
    }

    // Constructor đơn giản hơn
    public Product(int id, String name, String description, double price, int imageResource) {
        this.id = String.valueOf(id);
        this.name = name;
        this.description = description;
        this.avatar = ""; // Nếu không có ảnh URL
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public double getPrice() {
        return price;  // Trả về giá thực tế
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}