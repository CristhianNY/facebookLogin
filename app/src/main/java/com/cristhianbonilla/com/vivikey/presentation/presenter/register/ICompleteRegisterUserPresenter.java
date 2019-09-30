package com.cristhianbonilla.com.vivikey.presentation.presenter.register;

import android.content.Context;

import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.core.presentation.IBasePresenter;

public interface ICompleteRegisterUserPresenter extends IBasePresenter {

    void saveUser(Context context, User user);
    User checkStatus(User user);

}
