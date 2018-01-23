package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import co.za.geartronix.R;
import co.za.geartronix.presenters.DashboardPresenter;
import co.za.geartronix.views.IHomeView;

public class DashBoardActivity extends BaseSlideMenuActivity implements IHomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.dashboard_main_menu);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}