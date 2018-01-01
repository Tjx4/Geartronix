package co.za.geartronix.views;

import co.za.geartronix.presenters.ProfilePresenter;

public interface IProfileView  extends IBaseAsyncView {
    ProfilePresenter getPresenter();
}
