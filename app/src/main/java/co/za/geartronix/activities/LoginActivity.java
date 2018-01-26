package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import co.za.geartronix.R;
import co.za.geartronix.presenters.LoginPresenter;
import co.za.geartronix.views.ILoginView;

public class LoginActivity extends BaseSlideMenuActivity implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.login_menu);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        boolean allow = getPresenter().allowKeyDown(keyCode, event);

        if (allow && keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        getPresenter().handleBackButtonPressed();
    }
}