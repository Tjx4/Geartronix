package co.za.geartronix.activities;

import android.os.Bundle;
import co.za.geartronix.R;
import co.za.geartronix.presenters.RegistrationPresenter;
import co.za.geartronix.views.IRegistrationView;

public class RegistrationActivity extends BaseSlideMenuActivity implements IRegistrationView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.registration_menu);
    }

    @Override
    public RegistrationPresenter getPresenter() {
        return (RegistrationPresenter) presenter;
    }

    @Override
    public void setPresenter() {
        presenter = new RegistrationPresenter(this);
    }
}