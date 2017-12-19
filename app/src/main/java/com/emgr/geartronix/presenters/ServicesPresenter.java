package com.emgr.geartronix.presenters;

import android.view.View;
import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.ServicesActivity;
import com.emgr.geartronix.views.IServicesView;

public class ServicesPresenter extends BaseMenuPresenter implements IServicesPresenter {

    public ServicesPresenter(IServicesView iServicesView) {
        setDependanciesNoActionBar((BaseActivity) iServicesView, R.layout.activity_home);
        pageTitle = getActivity().getString(R.string.services);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.activity_service );
        setViews();
    }

    @Override
    protected void beforeAsyncCall() {

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

    }

    @Override
    public ServicesActivity getActivity() {
        return (ServicesActivity)activity;
    }

    @Override
    public void handleViewClickedEvent(View view) {

    }
}
