package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import co.za.geartronix.R;
import co.za.geartronix.presenters.GalleryPresenter;
import co.za.geartronix.views.IGalleryView;

public class GalleryActivity extends BaseAsyncActivity implements IGalleryView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter = new GalleryPresenter(this);
    }

    @Override
    public void onViewClickedEvent(View view) {
        getPresenter().handleViewClickedEvent(view);
    }

    @Override
    public GalleryPresenter getPresenter() {
        return (GalleryPresenter)presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if( itemId == android.R.id.home)
            onBackPressed();

        return true;
    }

    @Override
    public void onBackPressed() {
        if(getPresenter().enlarged) {
            getPresenter().hideEnlargedImage();
        }
        else {
            super.onBackPressed();
        }
    }

}