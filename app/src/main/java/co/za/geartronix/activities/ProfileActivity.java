package co.za.geartronix.activities;

import android.os.Bundle;
import co.za.geartronix.R;
import co.za.geartronix.presenters.ProfilePresenter;
import co.za.geartronix.views.IProfileView;

public class ProfileActivity extends BaseOverflowMenuActivity implements IProfileView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.profile_menu);
    }

    @Override
    public void setPresenter() {
        presenter = new ProfilePresenter(this);
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