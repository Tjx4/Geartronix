package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.View;
import co.za.geartronix.presenters.FindUsPresenter;
import co.za.geartronix.views.IFindUsView;

public class FindUsActivity  extends BaseAsyncActivity implements IFindUsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public FindUsPresenter getPresenter() {
        return (FindUsPresenter)presenter;
    }

    @Override
    public void setPresenter() {
        presenter = new FindUsPresenter(this);
    }

    @Override
    public void onViewClickedEvent(View view) {
        getPresenter().handleViewClickedEvent(view);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
    */

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}