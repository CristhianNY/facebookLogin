package com.cristhianbonilla.com.vivikey.presentation.iterator;

import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.core.presentation.IBasePresenter;

public interface IloginIterator {

    void get();

    void fetch();

    void responseOk();

    void responseNoContent();

    String add(User user);

    void delete();
}
