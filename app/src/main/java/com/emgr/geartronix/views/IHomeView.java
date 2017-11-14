package com.emgr.geartronix.views;

import com.emgr.geartronix.presenters.HomePresenter;

public interface IHomeView extends IBaseAsyncView{
    HomePresenter getPresenter();
}
