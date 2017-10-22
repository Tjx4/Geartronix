package com.emgr.geartronix.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.emgr.geartronix.R;
import com.emgr.geartronix.presenters.DashboardPresenter;
import com.emgr.geartronix.views.IDashboardView;

public class DashboardActivity extends BaseAsyncActivity implements IDashboardView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public DashboardPresenter getPresenter() {
        return (DashboardPresenter)presenter;
    }

    @Override
    public void setPresenter() {
        presenter = new DashboardPresenter(this);
    }

    @Override
    public void handleButtonsClickedEvent(View button) {

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
}
