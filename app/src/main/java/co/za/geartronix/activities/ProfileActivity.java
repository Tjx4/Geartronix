package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import co.za.geartronix.R;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
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
    public void onViewClickedEvent(View view) {
        getPresenter().handleViewClickedEvent(view);
    }

    @Override
    public ProfilePresenter getPresenter() {
        return (ProfilePresenter)presenter;
    }

    @Override
    public void onBackPressed() {

        if(getPresenter().imageEnlarged) {
            getPresenter().hideEnlargedImage();
        }
        else if(getPresenter().isCarVieOptend) {
            getPresenter().closeCarView(false);
        }
        else {
            if(getPresenter().isEditMode) {
                getPresenter().handleBackButtonPressed();
            }
            else {
                super.onBackPressed();
            }
        }

    }

}