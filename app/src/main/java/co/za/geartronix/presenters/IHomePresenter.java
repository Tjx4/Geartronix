package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.HomeActivity;

public interface IHomePresenter extends IBaseAsyncPresenter {
    HomeActivity getActivity();
    void setActiveColor(View view);
    void slideInTiles();
    void revertViewBackgroundColor(View view);
}