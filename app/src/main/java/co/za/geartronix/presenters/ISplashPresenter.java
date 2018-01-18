package co.za.geartronix.presenters;

import co.za.geartronix.activities.SplashActivity;

public interface ISplashPresenter extends IBaseAsyncPresenter {
    SplashActivity getActivity();
    void checkLinkedUser();
}
