package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.MenuItem;
import co.za.geartronix.R;
import co.za.geartronix.presenters.ReferralPresenter;
import co.za.geartronix.views.IReferralView;

public class ReferralActivity extends BaseOverflowMenuActivity implements IReferralView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.referral_menu);
    }

    @Override
    public void setPresenter() {
        presenter = new ReferralPresenter(this);
    }

    @Override
    public ReferralPresenter getPresenter() {
        return (ReferralPresenter)presenter;
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