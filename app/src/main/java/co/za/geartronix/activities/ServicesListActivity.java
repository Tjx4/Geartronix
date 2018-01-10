package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.presenters.ServicesListPresenter;
import co.za.geartronix.views.IServicesListView;

public class ServicesListActivity  extends BaseAsyncActivity implements IServicesListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter = new ServicesListPresenter(this);
    }

    @Override
    public void onViewClickedEvent(View view) {
        getPresenter().handleViewClickedEvent(view);
    }

    @Override
    public ServicesListPresenter getPresenter() {
        return (ServicesListPresenter)presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.services_menu, menu);
        getPresenter().configureActionBarItems(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if( itemId == android.R.id.home)
            onBackPressed();
        else
            getPresenter().menuOptionSelected(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
