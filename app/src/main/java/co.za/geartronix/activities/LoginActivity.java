package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.View;
import co.za.geartronix.presenters.LoginPresenter;
import co.za.geartronix.views.ILoginView;

public class LoginActivity extends BaseAsyncActivity implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public LoginPresenter getPresenter() {
        return (LoginPresenter)presenter;
    }

    @Override
    public void onViewClickedEvent(View view) {
        getPresenter().handleViewClickedEvent(view);
    }

}