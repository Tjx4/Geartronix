package co.za.geartronix.views;

import android.view.View;
import co.za.geartronix.presenters.HomePresenter;

public interface IHomeView extends IBaseAsyncView{
    HomePresenter getPresenter();
    void onTileClicked(View view);
}
