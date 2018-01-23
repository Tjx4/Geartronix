package co.za.geartronix.activities;

import android.os.Bundle;
import co.za.geartronix.R;
import co.za.geartronix.presenters.GalleryPresenter;
import co.za.geartronix.views.IGalleryView;

public class GalleryActivity extends BaseOverflowMenuActivity implements IGalleryView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.gallery_menu);
    }

    @Override
    public void setPresenter() {
        presenter = new GalleryPresenter(this);
    }

    @Override
    public GalleryPresenter getPresenter() {
        return (GalleryPresenter)presenter;
    }

    @Override
    public void onBackPressed() {
        if(getPresenter().imageEnlarged) {
            getPresenter().hideEnlargedImage();
        }
        else {
            super.onBackPressed();
        }
    }
}