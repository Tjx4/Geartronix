package co.za.geartronix.activities;

import android.os.Bundle;
import co.za.geartronix.R;
import co.za.geartronix.presenters.ServicesListPresenter;
import co.za.geartronix.views.IServicesListView;

public class ServicesListActivity extends BaseOverflowMenuActivity implements IServicesListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.services_menu);
    }

    @Override
    public void setPresenter() {
        presenter = new ServicesListPresenter(this);
    }

    @Override
    public ServicesListPresenter getPresenter() {
        return (ServicesListPresenter)presenter;
    }
}