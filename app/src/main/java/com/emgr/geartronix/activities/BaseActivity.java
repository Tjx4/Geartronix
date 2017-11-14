package com.emgr.geartronix.activities;
import android.support.v7.app.AppCompatActivity;
import com.emgr.geartronix.presenters.BasePresenter;

public abstract class BaseActivity extends AppCompatActivity {
    public BasePresenter presenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //presenter.activity = null;
    }
}