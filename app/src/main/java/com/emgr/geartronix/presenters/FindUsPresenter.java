package com.emgr.geartronix.presenters;

import android.view.View;

import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.views.IFindUsView;

public class FindUsPresenter extends BaseAppActivityPresenter {

    public FindUsPresenter(IFindUsView iFindUsView) {
        super((BaseActivity)iFindUsView);
    }

    public FindUsPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity);
        setIcon(R.mipmap.find_us_icon);
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
}