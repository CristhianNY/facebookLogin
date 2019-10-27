package com.cristhianbonilla.com.vivikey.core.domain;

public class ContactNumber {

    private String phoneNumber,owner;

    public ContactNumber(){

    }
    public ContactNumber(String phoneNumber,String owner) {
        this.phoneNumber = phoneNumber;
        this.owner = owner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
