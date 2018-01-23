package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import co.za.geartronix.R;
import co.za.geartronix.presenters.ServicesListPresenter;
import co.za.geartronix.views.IServicesListView;

public class ServicesListActivity  extends BaseMenuActivity implements IServicesListView {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getMenu(), menu);
        getPresenter().configureActionBarItems(menu);
        return super.onCreateOptionsMenu(menu);
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
}