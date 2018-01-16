package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.DashBoardActivity;

public interface IHomePresenter extends IBaseAsyncPresenter {
    DashBoardActivity getActivity();
    void setActiveColor(View view);
    void slideInTiles();
    void revertViewBackgroundColor(View view);
}