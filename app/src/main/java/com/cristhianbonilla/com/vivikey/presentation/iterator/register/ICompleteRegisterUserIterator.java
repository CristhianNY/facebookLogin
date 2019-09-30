package com.cristhianbonilla.com.vivikey.presentation.iterator.register;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.User;

public interface ICompleteRegisterUserIterator {

    void saveUser(Context context, User user);
    User checkIfUserExist(User user);
}
