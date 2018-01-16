package co.za.geartronix.presenters;

import co.za.geartronix.activities.AskActivity;

public interface IAskPresenter  extends IBaseAsyncPresenter {
    AskActivity getActivity();
    void addNewMessage();
}
