package com.cristhianbonilla.com.vivikey.presentation.view.dashboard.home;

import android.content.ContentResolver;

import com.cristhianbonilla.com.vivikey.core.domain.Contact;

import java.util.ArrayList;
import java.util.List;

public interface HomeFragmentView {

    void InsertContactsToFirebase(ContentResolver cr);
    List<Contact> getFriendsFromFirbase(ArrayList<Contact> myPhoneNumber);
}
