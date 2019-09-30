package com.cristhianbonilla.com.vivikey.presentation.iterator.login;

import com.cristhianbonilla.com.vivikey.presentation.iterator.login.*;
import com.facebook.accountkit.Account;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginIterator implements IloginIterator {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    User usuario;
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
    public void insertUser(Context context, Account user) {
        FirebaseApp.initializeApp(context);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference  = firebaseDatabase.getReference();
        databaseReference.child("User").child(user.getId()).setValue(user);
    }

    @Override
    public User checkIfUserExist(User user) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        databaseReference.orderByChild("id").equalTo(user.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                   usuario = childSnapshot.getValue(User.class);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return  usuario;
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
