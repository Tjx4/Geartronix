package co.za.geartronix.activities;

import android.os.Bundle;
import co.za.geartronix.R;
import co.za.geartronix.presenters.ServicesPresenter;
import co.za.geartronix.views.IServicesView;

public class ServicesActivity extends BaseOverflowMenuActivity implements  IServicesView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.dashboard_main_menu);
    }

    @Override
    public ServicesPresenter getPresenter() {
        return (ServicesPresenter)presenter;
    }

    @Override
    public void setPresenter() {
        presenter = new ServicesPresenter(this);
    }
}