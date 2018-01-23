package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.presenters.FindUsPresenter;
import co.za.geartronix.views.IFindUsView;

public class FindUsActivity extends BaseOverflowMenuActivity implements IFindUsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.findus_menu);
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
        getPresenter().handleAsyncButtonClickedEvent(view);
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