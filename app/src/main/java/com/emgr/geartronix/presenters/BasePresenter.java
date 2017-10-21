package com.emgr.geartronix.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.emgr.geartronix.activities.BaseActivity;

public class BasePresenter {

    protected Context context;
    public BaseActivity activity;

    public BasePresenter() {
    }

    protected ActionBar basicActionBarConfiguration(String title) {
        ActionBar ab = this.activity.getSupportActionBar();
        ab.setTitle(title);

        return ab;
    }

    protected void goToActivity(Class activity) {
        Intent i = new Intent(context, activity);
        this.activity.startActivity(i);
    }

    protected void goToActivityWithPayload(Class activity, Bundle bundle) {
        Bundle payload = bundle;
        Intent i = new Intent(context, activity);
        i.putExtra("payload", payload);
        this.activity.startActivity(i);
    }
}
