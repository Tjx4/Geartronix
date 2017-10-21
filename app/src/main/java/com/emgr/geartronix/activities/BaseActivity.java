package com.emgr.geartronix.activities;

import android.support.v7.app.AppCompatActivity;
import com.emgr.geartronix.presenters.BasePresenter;

public class BaseActivity extends AppCompatActivity {
    protected BasePresenter presenter;

    protected void comonOnCreate(int contentView) {
        setContentView(contentView);
    }


}