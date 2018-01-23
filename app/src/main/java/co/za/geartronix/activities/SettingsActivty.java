package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.MenuItem;
import co.za.geartronix.R;
import co.za.geartronix.presenters.SettingsPresenter;
import co.za.geartronix.views.ISettingsView;

public class SettingsActivty extends BaseOverflowMenuActivity implements ISettingsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.settings_menu);
    }
    @Override
    public void setPresenter() {
        presenter = new SettingsPresenter(this);
    }

    @Override
    public SettingsPresenter getPresenter() {
        return (SettingsPresenter)presenter;
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