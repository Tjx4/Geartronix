package com.emgr.geartronix.adapters;

import android.view.View;

import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.views.IDiagnosticsView;

class DiagnosticsPresenter extends com.emgr.geartronix.presenters.BaseAppActivityPresenter {

    public DiagnosticsPresenter(IDiagnosticsView iDiagnosticsView) {
    }

    public DiagnosticsPresenter(BaseActivity baseActivity, int index) {
        super();
        activity = baseActivity;
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
