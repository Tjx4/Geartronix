package com.emgr.geartronix.views;

import android.view.View;
import com.emgr.geartronix.presenters.LoginPresenter;

public interface ILoginViewI extends IBaseView {
    LoginPresenter getPresenter();
    void onLoginButtonClicked(View view);
    void onRegisterButtonClicked(View view);
    void onForgotPasswordClicked(View view);
}
