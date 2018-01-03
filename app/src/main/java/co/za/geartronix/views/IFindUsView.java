package co.za.geartronix.views;

import co.za.geartronix.presenters.FindUsPresenter;

public interface IFindUsView  extends IBaseAsyncView {
    FindUsPresenter getPresenter();
}
