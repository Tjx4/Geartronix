package com.emgr.geartronix.activities;

import android.os.Bundle;
import android.view.View;
import com.emgr.geartronix.presenters.LoginPresenter;
import com.emgr.geartronix.views.ILoginViewI;

public class LoginActivity extends BaseActivity implements ILoginViewI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
        getPresenter().onCreate();
    }

    @Override
    public void setPresenter() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public LoginPresenter getPresenter() {
        return (LoginPresenter)presenter;
    }

    @Override
    public void onLoginButtonClicked(View view) {
        getPresenter().loginUser();
    }

    @Override
    public void onRegisterButtonClicked(View view) {
        getPresenter().registerUser();
    }

    @Override
    public void onForgotPasswordClicked(View view) {
        getPresenter().resetPassword();
    }

}