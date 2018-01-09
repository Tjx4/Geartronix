package co.za.geartronix.views;

import co.za.geartronix.presenters.ServicesListPresenter;

public interface IServicesListView  extends IBaseAsyncView {
    ServicesListPresenter getPresenter();
}
