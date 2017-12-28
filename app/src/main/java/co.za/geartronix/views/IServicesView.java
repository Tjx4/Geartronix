package co.za.geartronix.views;

import co.za.geartronix.presenters.ServicesPresenter;

public interface IServicesView extends IBaseAsyncView{
    ServicesPresenter getPresenter();
}
