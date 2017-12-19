package com.emgr.geartronix.activities;

import android.os.Bundle;
import android.view.View;
import com.emgr.geartronix.presenters.GalleryPresenter;
import com.emgr.geartronix.views.IGalleryView;

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
    public void onBackPressed() {
        super.onBackPressed();
    }
}