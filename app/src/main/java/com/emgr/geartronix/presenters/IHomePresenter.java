package com.emgr.geartronix.presenters;

import android.view.MenuItem;
import android.view.View;

import com.emgr.geartronix.activities.HomeActivity;

public interface IHomePresenter extends IBaseAsyncPresenter {
    void menuOptionSelected(MenuItem item);
    void setViews();
    HomeActivity getActivity();
    void handleTileClicked(View view);
}
