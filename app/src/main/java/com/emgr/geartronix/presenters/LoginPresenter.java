package com.emgr.geartronix.presenters;

import android.support.v7.app.ActionBar;
import android.view.View;
import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.GalleryActivity;
import com.emgr.geartronix.views.ILoginView;

public class LoginPresenter extends BasePresenter implements ILoginPresenter {

    public LoginPresenter(ILoginView iLoginView) {
        activity = (BaseActivity) iLoginView;
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
        goToActivity(GalleryActivity.class);
    }

    @Override
    public void registerUser() {

    }

    @Override
    public void resetPassword() {

    }
}