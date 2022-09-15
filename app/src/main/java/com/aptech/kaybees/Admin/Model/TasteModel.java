package com.aptech.kaybees.Admin.Model;

public class TasteModel
{
    String name,description,price,disPrice,quantity;

    public TasteModel(String name, String description, String price, String disPrice, String quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.disPrice = disPrice;
        this.quantity = quantity;
    }


    public TasteModel() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDisPrice() {
        return disPrice;
    }

    public void setDisPrice(String disPrice) {
        this.disPrice = disPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
