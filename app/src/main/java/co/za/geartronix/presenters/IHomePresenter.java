package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.HomeActivity;

public interface IHomePresenter extends IBaseAsyncPresenter {
    void setViews();
    HomeActivity getActivity();
    void handleTileClicked(View view);
}