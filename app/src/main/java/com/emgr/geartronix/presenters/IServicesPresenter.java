package com.emgr.geartronix.presenters;

import com.emgr.geartronix.activities.ServicesActivity;

public interface IServicesPresenter extends IBaseAsyncPresenter {
    void setViews();
    ServicesActivity getActivity();
}
