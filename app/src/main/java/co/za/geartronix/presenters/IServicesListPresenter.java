package co.za.geartronix.presenters;

import co.za.geartronix.activities.ServicesListActivity;

public interface IServicesListPresenter  extends IBaseAsyncPresenter {
    ServicesListActivity getActivity();
    void showServices();
    void requestService();
}
