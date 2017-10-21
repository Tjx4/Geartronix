package com.emgr.geartronix.presenters;

import android.support.v7.app.ActionBar;
import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.views.ILoginViewI;

public class LoginPresenter extends BasePresenter implements ILoginPresenter {

    public LoginPresenter(ILoginViewI iLoginView) {
        activity = (BaseActivity) iLoginView;
    }

    @Override
    public void onCreate() {
        slideInActivity();
        comonOnCreate(R.layout.activity_login);
        configureActionBar();
    }

    @Override
    public void configureActionBar() {
        ActionBar ab = basicActionBarConfiguration(activity.getString(R.string.app_name));
        ab.setLogo(R.mipmap.ic_launcher);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        /*ab.setTitle(getResources().getString(R.string.app_name));
        ab.setDisplayHomeAsUpEnabled(true);*/
    }

    @Override
    public void loginUser() {
    }

    @Override
    public void registerUser() {

    }

    @Override
    public void resetPassword() {

    }
}