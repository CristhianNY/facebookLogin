package com.cristhianbonilla.com.vivikey.presentation.iterator;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaseIterator {

    protected FirebaseDatabase firebaseDatabase;
    protected DatabaseReference databaseReference;
    protected User usuario;


    protected void initFirebase(Context context) {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference  = firebaseDatabase.getReference();
    }
}
