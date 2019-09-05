package com.cristhianbonilla.com.vivikey.presentation.presenter.register;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.VivikeyApp;
import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.presentation.iterator.register.ICompleteRegisterUserIterator;

import javax.inject.Inject;

public class CompleteRegisterUserPresenter implements ICompleteRegisterUserPresenter {
    @Inject
    ICompleteRegisterUserIterator interactor;

    public CompleteRegisterUserPresenter(Context context) {
        VivikeyApp.getControllerComponent(context).inject(this);
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void onStart(Object view) {

    }

    @Override
    public void onStop() {

    }
}
