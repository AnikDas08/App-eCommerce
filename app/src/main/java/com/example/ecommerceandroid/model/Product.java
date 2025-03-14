package com.example.ecommerceandroid.model;

public class Product {

    private String price;
    private String details;
    private String name;
    private String pimage;
    private String roll;

    public Product() {
    }

    public Product(String price, String details, String name, String pimage,String roll) {
        this.price = price;
        this.details = details;
        this.name = name;
        this.pimage = pimage;
        this.roll=roll;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }
    public String getRoll(){
        return roll;
    }
    public void setRoll(String roll){
        this.roll=roll;
    }

}
