package com.cristhianbonilla.com.vivikey.presentation.presenter.Home;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.VivikeyApp;
import com.cristhianbonilla.com.vivikey.core.domain.Contact;
import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.presentation.iterator.Home.IHomeIterator;

import java.util.List;

import javax.inject.Inject;

public class HomePresenter implements IHomePresenter {
    @Inject
    IHomeIterator interactor;

    public HomePresenter(Context context) {
        VivikeyApp.getControllerComponent(context).inject(this);
    }

    @Override
    public List<User> getConstastsFriends(User user) {
        return null;
    }

    @Override
    public void insertContacts(List<Contact> contacts, User user, Context context) {

        interactor.insertContacts(user,contacts,context);
    }

    @Override
    public void onStart(Object view) {

    }

    @Override
    public void onStop() {

    }
}
