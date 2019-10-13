package com.cristhianbonilla.com.vivikey.presentation.presenter.Home;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.Contact;
import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.core.presentation.IBasePresenter;

import java.util.List;

public interface IHomePresenter extends IBasePresenter {
       List<User> getConstastsFriends(User user);
       void insertContacts(List<Contact> contacts, User user, Context context);
}
