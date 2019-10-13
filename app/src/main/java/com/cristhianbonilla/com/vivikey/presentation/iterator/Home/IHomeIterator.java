package com.cristhianbonilla.com.vivikey.presentation.iterator.Home;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.Contact;
import com.cristhianbonilla.com.vivikey.core.domain.User;

import java.util.List;

public interface IHomeIterator {

    void getContactsFriends();
    void insertContacts(User user, List<Contact> contacts, Context context);
}
