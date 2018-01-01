package co.za.geartronix.presenters;

import android.view.View;

public interface IBaseAsyncPresenter extends IBasePresenter{
    void handleViewClickedEvent(View view);
}
