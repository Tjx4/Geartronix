package co.za.geartronix.activities;

import co.za.geartronix.presenters.SplashPresenter;
import co.za.geartronix.views.ISplashView;

public class SplashActivity extends BaseOverflowMenuActivity implements ISplashView {

    @Override
    public void setPresenter() {
        presenter = new SplashPresenter(this);
    }

    @Override
    public SplashPresenter getPresenter() {
        return (SplashPresenter)presenter;
    }
}