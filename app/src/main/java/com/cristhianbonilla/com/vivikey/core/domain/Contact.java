package com.cristhianbonilla.com.vivikey.core.domain;

public class Contact {

    private String userId, name, phoneNumber,owner;

    public Contact() {

    }

    public Contact(String userId , String name, String phoneNumber,String owner) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.owner = owner;
    }

    public String getUserId() {
        return userId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
