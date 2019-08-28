package com.cristhianbonilla.com.vivikey.core.presentation.view;

public interface IBaseView {

    void showLoader();

    void hideLoader();

    void noContent();

    void noInternet();

    void showToast(String msg);
}