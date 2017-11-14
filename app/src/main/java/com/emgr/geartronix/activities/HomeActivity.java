package com.emgr.geartronix.activities;

import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import com.emgr.geartronix.R;
import com.emgr.geartronix.presenters.HomePresenter;
import com.emgr.geartronix.views.IHomeView;

public class HomeActivity extends BaseAsyncActivity implements IHomeView, NavigationView.OnNavigationItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public HomePresenter getPresenter() {
        return (HomePresenter)presenter;
    }

    @Override
    public void onTileClicked(View view) {
        getPresenter().handleTileClicked(view);
    }

    @Override
    public void setPresenter() {
        presenter = new HomePresenter(this);
    }

    @Override
    public void handleButtonsClickedEvent(View button) {
        // Handles click events for buttons on screen
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().menuOptionSelected(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        getPresenter().handleBackButtonPressed();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return getPresenter().handleNavigationItemSelected(item);
    }
}
