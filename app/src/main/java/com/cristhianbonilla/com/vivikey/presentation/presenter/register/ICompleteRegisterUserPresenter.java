package com.cristhianbonilla.com.vivikey.presentation.presenter.register;

import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.core.presentation.IBasePresenter;

public interface ICompleteRegisterUserPresenter extends IBasePresenter {

    void saveUser(User user);

}
