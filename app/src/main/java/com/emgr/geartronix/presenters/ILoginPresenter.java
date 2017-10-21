package com.emgr.geartronix.presenters;

import android.view.View;

public interface ILoginPresenter extends IBasePresenter {
    void configureActionBar();
    void loginUser();
    void registerUser();
    void resetPassword();
}
