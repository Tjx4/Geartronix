package co.za.geartronix.views;

import co.za.geartronix.presenters.HomePresenter;

public interface IHomeView extends IBaseAsyncView{
    HomePresenter getPresenter();
}