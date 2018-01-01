package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.View;
import co.za.geartronix.presenters.ProfilePresenter;
import co.za.geartronix.views.IProfileView;


public class ProfileActivity  extends BaseAsyncActivity implements IProfileView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter = new ProfilePresenter(this);
    }

    @Override
    public void onViewClickedEvent(View view) {
        getPresenter().handleViewClickedEvent(view);
    }

    @Override
    public ProfilePresenter getPresenter() {
        return (ProfilePresenter)presenter;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}