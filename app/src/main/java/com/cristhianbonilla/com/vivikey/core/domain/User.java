package com.cristhianbonilla.com.vivikey.core.domain;

import com.cristhianbonilla.com.vivikey.core.support.Assertion;

public class User implements Specification {
    private String id;
    private String username;
    private String email;
    private String phone;
    private boolean isRegistered;

    public User(){

    }
    public User(String id ,String username,String email , String phone, boolean isRegistered) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.isRegistered = isRegistered;
    }

    public boolean getIsRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override public String toString() {
        return username;
    }

    @Override public void isValid() throws Exception {
        Assertion assertion = new Assertion();
        assertion.isNull(username).isEmpty(username).errors("Username");
        assertion.isNull(email).isEmpty(email).isEmail(email).errors("E-mail");
    }
}