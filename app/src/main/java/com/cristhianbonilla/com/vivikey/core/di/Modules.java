package com.cristhianbonilla.com.vivikey.core.di;

import android.content.Context;


import com.cristhianbonilla.com.vivikey.presentation.iterator.login.*;
import com.cristhianbonilla.com.vivikey.presentation.presenter.login.ILoginPresenter;
import com.cristhianbonilla.com.vivikey.presentation.presenter.login.LoginPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class Modules {

    private Context context;

    public Modules(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public ILoginPresenter providesMainPresenter(){
        return new LoginPresenter(context);
    }

    @Provides @Singleton public IloginIterator providesMainInteractor(){
        return new LoginIterator();
    }

    @Provides public Context context() {
        return context;
    }
}