package co.za.geartronix.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import co.za.geartronix.R;
import co.za.geartronix.presenters.DashboardPresenter;
import co.za.geartronix.presenters.LoginPresenter;
import co.za.geartronix.views.IHomeView;
import co.za.geartronix.views.ILoginView;

public class LoginActivity extends BaseAsyncActivity implements ILoginView, NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public LoginPresenter getPresenter() {
        return (LoginPresenter) presenter;
    }

    @Override
    public void setPresenter() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public void onViewClickedEvent(View view) {
        getPresenter().handleViewClickedEvent(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
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