package com.cristhianbonilla.com.vivikey.presentation.view.login;

import com.cristhianbonilla.com.vivikey.core.presentation.view.IBaseView;
import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseUser;

public interface LoginView extends IBaseView {

    void prepareLogin();
    void handleFacebookAccessToken(AccessToken token);
    void checkLoginStatus();
    void updateUI(FirebaseUser user);
    void loadUserInformation(AccessToken accessToken);
    void getHash();
}