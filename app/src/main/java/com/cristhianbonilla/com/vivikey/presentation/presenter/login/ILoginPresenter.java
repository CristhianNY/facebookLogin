package com.cristhianbonilla.com.vivikey.presentation.presenter.login;

import com.cristhianbonilla.com.vivikey.core.presentation.IBasePresenter;

public interface ILoginPresenter extends IBasePresenter {
    void logon(String email, String user, String pass);
}