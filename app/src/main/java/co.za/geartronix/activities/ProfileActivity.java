package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.View;

import co.za.geartronix.views.IProfileView;


public class ProfileActivity  extends BaseAsyncActivity implements IProfileView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onViewClickedEvent(View button) {

    }
}