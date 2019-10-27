package com.cristhianbonilla.com.vivikey.presentation.iterator.Home;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.Contact;
import com.cristhianbonilla.com.vivikey.core.domain.ContactNumber;
import com.cristhianbonilla.com.vivikey.core.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface IHomeIterator {

    List<Contact> getContactsFriendsFromFirebase(Context context, ArrayList<Contact> contacts,String myPhoneNumber);
    void insertContacts(User user, List<Contact> contacts, Context context);
    void insertFriends(String myPhoneNumber, List<ContactNumber> contacts, Context context);

}
