package com.example.ecommerceandroid.model;

public class OrderInsert {
    String rolls,name,details,userName,userEmail,userNumber,qualtity,image,price;

    public OrderInsert(String rolls, String name, String details, String userName, String userEmail, String userNumber, String qualtity, String image, String price) {
        this.rolls = rolls;
        this.name = name;
        this.details = details;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.qualtity = qualtity;
        this.image = image;
        this.price = price;
    }

    public OrderInsert() {
    }

    public String getRolls() {
        return rolls;
    }

    public void setRolls(String rolls) {
        this.rolls = rolls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getQualtity() {
        return qualtity;
    }

    public void setQualtity(String qualtity) {
        this.qualtity = qualtity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
