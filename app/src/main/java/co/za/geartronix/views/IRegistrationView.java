package co.za.geartronix.views;


import co.za.geartronix.presenters.RegistrationPresenter;

public interface IRegistrationView  extends IBaseAsyncView {
    RegistrationPresenter getPresenter();
}
