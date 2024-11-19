package com.example.ecommerceandroid.model;

public class OrderShow {
    String course,name,quantity,contact,image;

    public OrderShow() {
    }

    public OrderShow(String course, String name, String quantity, String contact, String image) {
        this.course = course;
        this.name = name;
        this.quantity = quantity;
        this.contact = contact;
        this.image = image;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
