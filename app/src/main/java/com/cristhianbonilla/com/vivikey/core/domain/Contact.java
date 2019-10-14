package com.cristhianbonilla.com.vivikey.core.domain;

public class Contact {

    private String userId, name, phoneNumber;

    public Contact(String userId ,String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
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
