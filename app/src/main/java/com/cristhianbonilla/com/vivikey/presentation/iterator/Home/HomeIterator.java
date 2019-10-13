package com.cristhianbonilla.com.vivikey.presentation.iterator.Home;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.Contact;
import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.presentation.iterator.BaseIterator;
import com.cristhianbonilla.com.vivikey.presentation.presenter.Home.HomePresenter;

import java.util.List;

public class HomeIterator extends BaseIterator implements IHomeIterator {
    @Override
    public void getContactsFriends() {


    }

    @Override
    public void insertContacts(User user, List<Contact> contacts, Context context) {
        initFirebase(context);
        databaseReference.getDatabase().getReference().getDatabase().getReference()
        databaseReference.child("contacts").child(user.getId()).setValue(contacts);
    }
}
