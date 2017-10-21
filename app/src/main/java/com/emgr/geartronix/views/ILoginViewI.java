package com.emgr.geartronix.views;

import android.view.View;
import com.emgr.geartronix.presenters.LoginPresenter;

public interface ILoginViewI extends IBaseAsyncView {
    LoginPresenter getPresenter();
}
