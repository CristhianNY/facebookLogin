package com.cristhianbonilla.com.vivikey.presentation.presenter.Home;

import com.cristhianbonilla.com.vivikey.core.domain.User;

import java.util.List;

public interface IHomePresenter {
       List<User>getFriends(User user);
}
