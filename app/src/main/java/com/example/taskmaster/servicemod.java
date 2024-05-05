package com.example.taskmaster;

public class servicemod {
    private String categoryName;
    private int serviceID;

    private String subcategory;
    private int price;
    private String description;
    private String imageUrl;

    public servicemod() {
    }

    public servicemod(String categoryName, int serviceID, String subcategory, int price, String description, String imageUrl) {
        this.categoryName = categoryName;
        this.serviceID = serviceID;
        this.subcategory = subcategory;
        this.price = price;
        this.description = description;
        this.imageUrl=imageUrl;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {return imageUrl; }

}