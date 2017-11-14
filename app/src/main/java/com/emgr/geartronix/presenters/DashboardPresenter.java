package com.emgr.geartronix.presenters;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.GalleryActivity;
import com.emgr.geartronix.models.AccountModel;
import com.emgr.geartronix.views.IDashboardView;

public class DashboardPresenter extends BaseAsyncPresenter implements IDashboardPresenter{

    private AccountModel responseModel;

    public DashboardPresenter(IDashboardView iDashboardView) {
        setDependancies((BaseActivity)iDashboardView, R.layout.activity_dashboard2);
        responseModel = new AccountModel();

        try {

            String user = activity.getIntent().getExtras().getBundle("payload").getString("user");
            showShortToast("Welcome "+user);
        }
        catch (Exception e){

            slideOutActivity();
        }

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

    @Override
    public void menuOptionSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_gallery:
                goToActivity(GalleryActivity.class);
                break;
        }
    }
}
