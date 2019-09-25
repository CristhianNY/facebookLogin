package com.cristhianbonilla.com.vivikey.core.di;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.presentation.presenter.login.LoginPresenter;
import com.cristhianbonilla.com.vivikey.presentation.presenter.register.CompleteRegisterUserPresenter;
import com.cristhianbonilla.com.vivikey.presentation.view.login.LoginActivity;
import com.cristhianbonilla.com.vivikey.presentation.view.register.CompleteRegisterUserActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = Modules.class)
public interface Components {

    Context context();

    void inject(LoginPresenter loginPresenter);
    void inject(LoginActivity loginActivity);
    void inject(CompleteRegisterUserActivity completeRegisterUserActivity);
    void inject(CompleteRegisterUserPresenter completeRegisterUserPresenter);
}
