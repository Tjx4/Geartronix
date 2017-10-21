package com.emgr.geartronix.presenters;


import android.view.View;

public interface ILoginPresenter extends IBaseAsyncPresenter {
    void onLoginButtonClicked(View view);
    void onRegisterClicked(View view);
    void onForgotPasswordClicked(View view);
    void setLoginDetails();
}
