package com.cristhianbonilla.com.vivikey.presentation.iterator.register;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompleteRegisterUserIterator implements ICompleteRegisterUserIterator {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    public void saveUser(Context context ,User user) {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference  = firebaseDatabase.getReference();
        databaseReference.child("User").child(user.getId()).setValue(user);
    }
}
