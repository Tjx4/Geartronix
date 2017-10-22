package com.emgr.geartronix.presenters;

import android.view.View;
import android.widget.Toast;

import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.models.AccountModel;
import com.emgr.geartronix.views.IDashboardView;

public class DashboardPresenter extends BaseAsyncPresenter implements IDashboardPresenter{

    private AccountModel responseModel;

    public DashboardPresenter(IDashboardView iDashboardView) {
        setDependancies((BaseActivity)iDashboardView, R.layout.activity_dashboard2);
        //setViews();
        responseModel = new AccountModel();

        String user = activity.getIntent().getExtras().getBundle("payload").getString("user");
        showShortToast("Welcome "+user);
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
    public void saveLoginDetails() {
        //String user = activity.getIntent().getExtras().getBundle("payload").getString("user");
    }
}
