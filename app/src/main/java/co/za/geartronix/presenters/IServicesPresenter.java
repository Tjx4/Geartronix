package co.za.geartronix.presenters;

import co.za.geartronix.activities.ServicesActivity;

public interface IServicesPresenter extends IBaseAsyncPresenter {
    void setViews();
    ServicesActivity getActivity();
}
