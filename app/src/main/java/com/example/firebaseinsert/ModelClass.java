package com.example.firebaseinsert;

public class ModelClass {

    String contact;
    String course;
    String name;
    String pimage;
    String roll;

    public ModelClass() {
    }

    public ModelClass(String contact, String course, String name, String pimage,String roll) {
        this.contact = contact;
        this.course = course;
        this.name = name;
        this.pimage = pimage;
        this.roll=roll;
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
    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
