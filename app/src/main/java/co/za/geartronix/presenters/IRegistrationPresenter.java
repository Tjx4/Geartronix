package co.za.geartronix.presenters;

import co.za.geartronix.activities.RegistrationActivity;

public interface IRegistrationPresenter extends IBaseAsyncPresenter {
    RegistrationActivity getActivity();
    void setRegProperties();
    void registerUser();
}
