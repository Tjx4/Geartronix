package com.emgr.geartronix.activities;

import android.os.Bundle;
import android.view.View;
import com.emgr.geartronix.R;
import com.emgr.geartronix.presenters.LoginPresenter;
import com.emgr.geartronix.views.ILoginView;

public class LoginActivity extends BaseActivity implements ILoginView{

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comonOnCreate(R.layout.activity_main);

        presenter = new LoginPresenter(this);
        loginPresenter = (LoginPresenter)presenter;
    }

    @Override
    public void onLoginButtonClicked(View view) {
        loginPresenter.loginUser();
    }

    @Override
    public void onRegisterButtonClicked(View view) {
        loginPresenter.registerUser();
    }

    @Override
    public void onForgotPasswordClicked(View view) {
        loginPresenter.resetPassword();
    }
}