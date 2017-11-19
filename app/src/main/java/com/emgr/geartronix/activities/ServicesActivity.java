package com.emgr.geartronix.activities;

import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.emgr.geartronix.R;
import com.emgr.geartronix.presenters.HomePresenter;
import com.emgr.geartronix.presenters.ServicesPresenter;
import com.emgr.geartronix.views.IServicesView;

public class ServicesActivity extends BaseAsyncActivity implements  IServicesView, NavigationView.OnNavigationItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public ServicesPresenter getPresenter() {
        return (ServicesPresenter)presenter;
    }

    @Override
    public void setPresenter() {
        presenter = new ServicesPresenter(this);
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
        super.onBackPressed();
        getPresenter().handleBackButtonPressed();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return getPresenter().handleNavigationItemSelected(item);
    }
}
