package com.aptech.kaybees.Consumer.Model;

public class AddToCartData
{

    String title;
    String disPrice;
    String quantity;
    String totalPRicee;
    String tasteId;
    String userId;

    public AddToCartData(String title, String disPrice, String quantity, String totalPRicee, String tasteId, String userId) {
        this.title = title;
        this.disPrice = disPrice;
        this.quantity = quantity;
        this.totalPRicee = totalPRicee;
        this.tasteId = tasteId;
        this.userId = userId;
    }

    public AddToCartData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTotalPRicee() {
        return totalPRicee;
    }

    public void setTotalPRicee(String totalPRicee) {
        this.totalPRicee = totalPRicee;
    }

    public String getTasteId() {
        return tasteId;
    }

    public void setTasteId(String tasteId) {
        this.tasteId = tasteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
