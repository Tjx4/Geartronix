package com.emgr.geartronix.presenters;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.emgr.geartronix.activities.HomeActivity;

public interface IHomePresenter extends IBaseAsyncPresenter {
    void menuOptionSelected(MenuItem item);
    void setViews();
    HomeActivity getActivity();
    void handleTileClicked(View view);


    boolean handleonPrepareOptionsMenu(Menu menu);
    void handleBackButtonPressed();
    boolean handleNavigationItemSelected(MenuItem item);
}
