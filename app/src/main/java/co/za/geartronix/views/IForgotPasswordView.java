package co.za.geartronix.views;

import co.za.geartronix.presenters.ForgotPasswordPresenter;

public interface IForgotPasswordView extends IBaseAsyncView {
    ForgotPasswordPresenter getPresenter();
}
