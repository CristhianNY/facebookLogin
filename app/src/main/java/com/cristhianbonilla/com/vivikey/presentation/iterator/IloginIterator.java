package com.cristhianbonilla.com.vivikey.presentation.iterator;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.core.presentation.IBasePresenter;

public interface IloginIterator {

    void get();

    void insertUser(Context context, User user);

    void fetch();

    void responseOk();

    void responseNoContent();

    String add(User user);

    void delete();
}
