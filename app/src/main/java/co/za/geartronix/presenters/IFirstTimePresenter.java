package co.za.geartronix.presenters;

import co.za.geartronix.activities.FirstTimeActivity;

public interface IFirstTimePresenter extends IBaseAsyncPresenter {
    FirstTimeActivity getActivity();
    void registerNewUser();
    void signinWithExistingUser();
}