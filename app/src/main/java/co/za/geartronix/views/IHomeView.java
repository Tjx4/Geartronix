package co.za.geartronix.views;

import co.za.geartronix.presenters.DashboardPresenter;

public interface IHomeView extends IBaseAsyncView{
    DashboardPresenter getPresenter();
}