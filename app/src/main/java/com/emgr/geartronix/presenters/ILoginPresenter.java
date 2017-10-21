package com.emgr.geartronix.presenters;


import android.view.View;

public interface ILoginPresenter extends IBaseAsyncPresenter {
    void onLoginButtonClicked(View button);
    void onRegisterClicked();
    void onForgotPasswordClicked();
    void setLoginDetails();
}
