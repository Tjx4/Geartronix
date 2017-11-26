package com.emgr.geartronix.presenters;

import android.view.View;
import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.GalleryActivity;
import com.emgr.geartronix.views.IGalleryView;


public class GalleryPresenter extends BaseAsyncPresenter implements IGalleryPresenter {


    public GalleryPresenter(IGalleryView iGalleryView) {
        setDependanciesChildActivities((BaseActivity) iGalleryView, R.layout.activity_gallery);
        setPageTitle(getActivity().getString(R.string.Gallery));
        setViews();

    }

    @Override
    protected void beforeAsyncCall() {
        showLoadingScreen();
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    public void setViews() {
        setAsyncViews();

    }

    @Override
    public GalleryActivity getActivity() {
        return (GalleryActivity) activity;
    }

    @Override
    public void handleButtonClickedEvent(View view) {

    }

    @Override
    public void openDetailedView(View view) {

    }

    @Override
    public void closeDetailedView(View view) {

    }

    @Override
    public void porpulateGrid() {

    }
}