package com.cristhianbonilla.com.vivikey.presentation.presenter.login;
import android.content.Context;
import com.cristhianbonilla.com.vivikey.core.VivikeyApp;
import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.presentation.iterator.login.IloginIterator;
import com.facebook.accountkit.Account;


import java.util.List;

import javax.inject.Inject;

public class LoginPresenter implements ILoginPresenter {
    @Inject
    IloginIterator interactor;

    public LoginPresenter(Context context) {
        VivikeyApp.getControllerComponent(context).inject(this);
    }

    @Override
    public void logon(Context context, User user) {

        interactor.insertUser(context,user);
    }

    @Override
    public void logon(Context context, Account user) {
        interactor.insertUser(context,user);
    }

    @Override
    public User checkStatus(User user) {
       return interactor.checkIfUserExist(user);
    }

    @Override
    public void onStart(Object view) {

    }

    @Override
    public void onStop() {

    }
}
