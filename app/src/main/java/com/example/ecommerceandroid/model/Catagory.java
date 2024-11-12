package com.example.ecommerceandroid.model;

public class Catagory {
    String name;
    int cimage;

    public Catagory(String name, int cimage) {
        this.name = name;
        this.cimage = cimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCimage() {
        return cimage;
    }

    public void setCimage(int cimage) {
        this.cimage = cimage;
    }
}
