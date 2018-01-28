package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.View;

import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.FirstTimeActivity;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.views.FirstTimeView;

public class FirstTimePresenter extends BaseAppActivityPresenter implements IFirstTimePresenter {


    public FirstTimePresenter(FirstTimeView iSplashView) {
        super((BaseActivity)iSplashView );
        setDependanciesNoActionBar(R.layout.activity_first_time);
        setViews();
    }

    public FirstTimePresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.ic_launcher);
        setDisplayName("");
    }

    @Override
    public void setViews() {
    }

    @Override
    public FirstTimeActivity getActivity() {
        return (FirstTimeActivity)activity;
    }

    @Override
    public void registerNewUser() {
        goToRegistration();
        getActivity().finish();
    }

    @Override
    public void signinWithExistingUser() {
        Bundle extra = new Bundle();
        extra.putBoolean(Constants.UNLINKEDUSER, true);
        goToLogin(extra);
        getActivity().finish();
    }

    @Override
    protected void beforeAsyncCall() {
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    @Override
    protected void postAnimation(View view) {
        clickedViewId = view.getId();

        switch (clickedViewId) {
            case R.id.btnCreateAccount:
                registerNewUser();
                break;
            case R.id.btnLoginExistingUser:
                signinWithExistingUser();
                break;
        }
    }

    @Override
    protected Object doAsyncOperation(int actionIndex) throws Exception {
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

    }
}