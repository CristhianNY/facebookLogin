package com.cristhianbonilla.com.vivikey.core.di;

import android.content.Context;


import com.cristhianbonilla.com.vivikey.presentation.iterator.login.*;
import com.cristhianbonilla.com.vivikey.presentation.iterator.login.*;
import com.cristhianbonilla.com.vivikey.presentation.iterator.register.CompleteRegisterUserIterator;
import com.cristhianbonilla.com.vivikey.presentation.iterator.register.ICompleteRegisterUserIterator;
import com.cristhianbonilla.com.vivikey.presentation.presenter.login.ILoginPresenter;
import com.cristhianbonilla.com.vivikey.presentation.presenter.login.LoginPresenter;
import com.cristhianbonilla.com.vivikey.presentation.presenter.register.CompleteRegisterUserPresenter;
import com.cristhianbonilla.com.vivikey.presentation.presenter.register.ICompleteRegisterUserPresenter;

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

    @Provides
    @Singleton
    public ICompleteRegisterUserPresenter completeRegisterUserPresenter(){
        return new CompleteRegisterUserPresenter(context);
    }

    @Provides @Singleton public ICompleteRegisterUserIterator provideCompleteRegisterIterator(){
        return new CompleteRegisterUserIterator();
    }


    @Provides public Context context() {
        return context;
    }
}