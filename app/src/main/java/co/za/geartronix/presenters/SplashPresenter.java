package co.za.geartronix.presenters;

import android.os.Bundle;
import android.widget.ProgressBar;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.SplashActivity;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.views.ISplashView;

public class SplashPresenter extends BaseAppActivityPresenter implements ISplashPresenter {

    ProgressBar startingProgress;
    private UserModel user;

    public SplashPresenter(ISplashView iSplashView) {
        super((BaseActivity)iSplashView );
        setDependanciesNoActionBar(R.layout.splash_content);
        setViews();
        checkLinkedUser();
        new DoAsyncCall().execute();
    }

    public SplashPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.ic_launcher);
        setDisplayName("");
    }

    @Override
    public void setViews() {
        startingProgress = (ProgressBar)getActivity().findViewById(R.id.prbSplashProgress);
        //startingProgress.setProgress(0);
    }

    @Override
    public SplashActivity getActivity() {
        return (SplashActivity)activity;
    }

    @Override
    public void checkLinkedUser() {
        try {
            user = sqLiteProvider.getUser(1);
        }
        catch (Exception e){

        }
    }

    @Override
    protected void beforeAsyncCall() {
    }

    @Override
    protected void duringAsyncCall(Integer... values) {
        startingProgress.setProgress(values[0]);
    }

    @Override
    protected Object doAsyncOperation(int actionIndex) throws Exception {
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

        if(user == null) {
            goToFirstime();
        }
        else {
            Bundle extras = new Bundle();
            extras.putInt(Constants.USERID, user.getId());
            goToLogin(extras);
        }

        getActivity().finish();
    }
}