package com.cristhianbonilla.com.vivikey.presentation.presenter.Home;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.VivikeyApp;
import com.cristhianbonilla.com.vivikey.core.domain.Contact;
import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.presentation.iterator.Home.IHomeIterator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomePresenter implements IHomePresenter {
    @Inject
    IHomeIterator iterator;

    public HomePresenter(Context context) {
        VivikeyApp.getControllerComponent(context).inject(this);
    }

    @Override
    public List<User> getConstastsFriends(User user) {
        return null;
    }

    @Override
    public void insertContacts(List<Contact> contacts, User user, Context context) {

        iterator.insertContacts(user,contacts,context);
    }

    @Override
    public  List<Contact> getFriendFromFirebase(Context context, ArrayList<Contact> contacts, String myPhoneNumber) {

       return iterator.getContactsFriendsFromFirebase(context, contacts, myPhoneNumber);
    }

    @Override
    public void onStart(Object view) {

    }

    @Override
    public void onStop() {

    }
}
