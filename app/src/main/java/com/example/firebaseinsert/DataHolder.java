package com.example.firebaseinsert;

public class DataHolder {
    String name,contact,course,pimage,roll;

    public DataHolder() {
    }

    public DataHolder(String name, String contact, String course, String pimage, String roll) {
        this.name = name;
        this.contact = contact;
        this.course = course;
        this.pimage = pimage;
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String pimage) {
        this.roll = pimage;
    }


}
