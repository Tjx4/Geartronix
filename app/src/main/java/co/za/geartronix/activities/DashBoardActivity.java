package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import co.za.geartronix.R;
import co.za.geartronix.presenters.DashboardPresenter;
import co.za.geartronix.views.IHomeView;

public class DashBoardActivity extends BaseAsyncActivity implements IHomeView, NavigationView.OnNavigationItemSelectedListener {

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
    public void onViewClickedEvent(View view) {
        getPresenter().handleViewClickedEvent(view);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return getPresenter().handleNavigationItemSelected(item);
    }
}