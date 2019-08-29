package com.cristhianbonilla.com.vivikey.presentation.iterator;

import android.app.Application;
import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginIterator implements  IloginIterator{


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    public void get() {

    }

    @Override
    public void insertUser(Context context, User user) {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference  = firebaseDatabase.getReference();
        databaseReference.child("User").child(user.getId()).setValue(user);
    }

    @Override
    public void fetch() {

    }

    @Override
    public void responseOk() {

    }

    @Override
    public void responseNoContent() {

    }

    @Override
    public String add(User user) {
        return null;
    }

    @Override
    public void delete() {

    }
}
