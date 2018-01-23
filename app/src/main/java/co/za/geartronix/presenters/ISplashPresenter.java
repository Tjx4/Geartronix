package co.za.geartronix.presenters;

import co.za.geartronix.activities.SplashActivity;
import co.za.geartronix.models.UserModel;

public interface ISplashPresenter extends IBaseAsyncPresenter {
    SplashActivity getActivity();
    void checkLinkedUser();
}