package co.za.geartronix.activities;

import android.view.Menu;
import android.view.MenuItem;

public abstract class BaseOverflowMenuActivity extends BaseActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getPresenter().handleBackButtonPressed();
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
            getPresenter().menuOptionSelected(item);

        return true;
    }

}