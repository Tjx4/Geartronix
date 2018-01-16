package co.za.geartronix.views;

import co.za.geartronix.presenters.AskPresenter;

public interface IAskView extends IBaseAsyncView{
    AskPresenter getPresenter();
}
