package co.za.geartronix.activities;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public abstract class BaseMenuActivity extends BaseActivity {

    public void onViewClickedEvent(View view) {
        getPresenter().handleAsyncButtonClickedEvent(view);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getPresenter().handleBackButtonPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getMenu(), menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().menuOptionSelected(item);
        return true;
    }

}