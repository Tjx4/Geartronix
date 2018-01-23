package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.MenuItem;
import co.za.geartronix.R;
import co.za.geartronix.presenters.ForgotPasswordPresenter;
import co.za.geartronix.views.IForgotPasswordView;

public class ForgotPasswordActivity extends BaseMenuActivity implements IForgotPasswordView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.forgot_password_menu);
    }

    @Override
    public void setPresenter() {
        presenter = new ForgotPasswordPresenter(this);
    }

    @Override
    public ForgotPasswordPresenter getPresenter() {
        return (ForgotPasswordPresenter)presenter;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if( itemId == android.R.id.home)
            onBackPressed();
        else
            super.onOptionsItemSelected(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}