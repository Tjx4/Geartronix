package com.emgr.geartronix.views;

import com.emgr.geartronix.presenters.DashboardPresenter;

public interface IDashboardView extends IBaseAsyncView{
    DashboardPresenter getPresenter();
}
