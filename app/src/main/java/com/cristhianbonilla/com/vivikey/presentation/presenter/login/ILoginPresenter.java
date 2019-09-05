package com.cristhianbonilla.com.vivikey.presentation.presenter.login;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.core.presentation.IBasePresenter;
import com.facebook.accountkit.Account;



public interface ILoginPresenter extends IBasePresenter {
    void logon(Context context, User user);
    void logon(Context context, Account user);
}