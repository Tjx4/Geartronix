package com.emgr.geartronix.activities;

import android.os.Bundle;
import android.view.View;
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
}
