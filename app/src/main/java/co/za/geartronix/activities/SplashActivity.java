package co.za.geartronix.activities;

import android.os.Bundle;
import co.za.geartronix.presenters.SplashPresenter;
import co.za.geartronix.views.ISplashView;

public class SplashActivity extends BaseMenuActivity implements ISplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter = new SplashPresenter(this);
    }

    @Override
    public SplashPresenter getPresenter() {
        return (SplashPresenter)presenter;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}