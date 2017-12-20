package com.emgr.geartronix.activities;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.emgr.geartronix.presenters.BasePresenter;

public abstract class BaseActivity extends AppCompatActivity {
    public BasePresenter presenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //presenter.activity = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.slideOutActivity();
        presenter.isBack = true;

    }

}