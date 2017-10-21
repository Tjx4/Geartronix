package com.emgr.geartronix.views;

import com.emgr.geartronix.presenters.LoginPresenter;

public interface ILoginView extends IBaseAsyncView {
    LoginPresenter getPresenter();
}
