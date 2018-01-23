package co.za.geartronix.activities;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;

public abstract class BaseSlideMenuActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private int menu2;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return getPresenter().handleNavigationItemSelected(item);
    }
}