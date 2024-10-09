package com.maxinetabugon.ecommerceui;

public class ProductModel {
    int image;
    String brand;
    String itemName;
    String price;

    public ProductModel(int image, String brand, String itemName, String price) {
        this.image = image;
        this.brand = brand;
        this.itemName = itemName;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getBrand() {
        return brand;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPrice() {
        return price;
    }
}
