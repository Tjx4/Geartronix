package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.SplashActivity;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.providers.MockProvider;
import co.za.geartronix.views.ISplashView;

public class SplashPresenter extends BaseAppActivityPresenter implements ISplashPresenter {

    ProgressBar startingProgress;
    private UserModel user;

    public SplashPresenter(ISplashView iSplashView) {
        super((BaseActivity)iSplashView );
        setDependanciesNoActionBar(R.layout.splash_content);
        setViews();
        checkLinkedUser();
    }

    public SplashPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.ic_launcher);
        setDisplayName(activity.getString(R.string.register));
    }

    @Override
    public void handleViewClickedEvent(View view) {

    }

    @Override
    public void setViews() {
        startingProgress = (ProgressBar)getActivity().findViewById(R.id.prbSplashProgress);
        startingProgress.setProgress(0);
    }

    @Override
    public SplashActivity getActivity() {
        return (SplashActivity)activity;
    }

    @Override
    public void checkLinkedUser() {
        user = new MockProvider(getActivity()).getMockUser();
        new DoAsyncCall().execute();
    }

    @Override
    protected void beforeAsyncCall() {

    }

    @Override
    protected void duringAsyncCall(Integer... values) {
        startingProgress.setProgress(values[0]);
    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
        try {

            Thread.sleep(10);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

        if(user == null) {
            goToRegistration();
        }
        else{
            Bundle extras = new Bundle();

            try {
                extras.putByteArray("User", object2Bytes(user));

            } catch (Exception e) {
                e.printStackTrace();
            }

            goToLogin(extras);
        }

        getActivity().finish();
    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }
}