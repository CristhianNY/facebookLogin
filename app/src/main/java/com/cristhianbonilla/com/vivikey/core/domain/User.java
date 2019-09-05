package com.cristhianbonilla.com.vivikey.core.domain;

import com.cristhianbonilla.com.vivikey.core.support.Assertion;

import java.util.UUID;

public class User implements Specification {
    private String id;
    private String username;
    private String email;

    public User(String id ,String username,String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
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

    @Override public String toString() {
        return username;
    }

    @Override public void isValid() throws Exception {
        Assertion assertion = new Assertion();
        assertion.isNull(username).isEmpty(username).errors("Username");
        assertion.isNull(email).isEmpty(email).isEmail(email).errors("E-mail");
    }
}