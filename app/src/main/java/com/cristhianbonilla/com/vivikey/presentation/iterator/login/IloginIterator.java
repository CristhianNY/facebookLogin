package com.cristhianbonilla.com.vivikey.presentation.iterator.login;

import com.facebook.accountkit.Account;
import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.User;

public interface IloginIterator {

    void get();

    void insertUser(Context context, User user);

    void insertUser(Context context, Account user);

    void fetch();

    void responseOk();

    void responseNoContent();

    String add(User user);

    void delete();
}