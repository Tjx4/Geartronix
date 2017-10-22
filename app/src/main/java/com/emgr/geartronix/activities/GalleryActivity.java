
package com.emgr.geartronix.activities;


import android.os.Bundle;
import android.view.View;

import com.emgr.geartronix.presenters.GallaryPresenter;
import com.emgr.geartronix.views.IGalleryView;

public class GalleryActivity extends BaseActivity implements IGalleryView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter = new GallaryPresenter(this);
    }

    @Override
    public void handleButtonsClickedEvent(View button) {

    }
}