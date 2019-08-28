package com.cristhianbonilla.com.vivikey.presentation.presenter.login;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.VivikeyApp;

public class LoginPresenter implements ILoginPresenter {

    public LoginPresenter(Context context) {
        VivikeyApp.getControllerComponent(context).inject(this);
    }
    @Override
    public void logon(String email, String user, String pass) {

    }

    @Override
    public void onStart(Object view) {

    }

    @Override
    public void onStop() {

    }
}
