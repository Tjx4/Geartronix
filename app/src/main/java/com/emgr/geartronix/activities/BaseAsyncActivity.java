package com.emgr.geartronix.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Toast;

import com.emgr.geartronix.constants.Constants;
import com.emgr.geartronix.presenters.BasePresenter;
import com.emgr.geartronix.providers.HttpConnectionProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseAsyncActivity extends BaseActivity {

    protected final String BASE_LOG = "base_log";
    protected boolean isBusy;
    protected int httpConTimeout = 6000;
    protected String myServices = "demos/mywebservices/";
    protected Context context;
    protected BasePresenter presenter;
    protected int activityLayout;
    protected Animation rotationAnimation;
    protected List<View> activeButtons;

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activeButtons = new ArrayList<>();
    }

    protected void setDependencies(Object...dependencies) {
        context = (Context)dependencies[0];
        activityLayout = (int)dependencies[1];
        setContentView(activityLayout);
        presenter = (BasePresenter)dependencies[2];
    }

    protected void configueActionBar(String tittle, int icon) {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(tittle);
        ab.setIcon(icon);
    }

    protected int[] getWidthAndHeight() {
        int Measuredwidth = 0;
        int Measuredheight = 0;
        Point size = new Point();
        WindowManager w = getWindowManager();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)    {
            w.getDefaultDisplay().getSize(size);
            Measuredwidth = size.x;
            Measuredheight = size.y;
        }else{
            Display d = w.getDefaultDisplay();
            Measuredwidth = d.getWidth();
            Measuredheight = d.getHeight();
        }

        return new int[]{Measuredwidth, Measuredheight};
    }

    protected int getWidth() { return getWidthAndHeight()[1];}
    protected int getHeight() {return getWidthAndHeight()[1];}
    protected void openNewActivityWithNoPayload(Class activity) {
        Intent i = createIntent(activity);
        callStartActivity(i);
    }

    protected void openNewActivityWithPayload(Class activity, Bundle payload) {
        Intent i = createIntent(activity);
        i.putExtras(payload);
        callStartActivity(i);
    }

    private Intent createIntent(Class activity) {
        return new Intent(context, activity);
    }

    private void callStartActivity(Intent i) {
        startActivity(i);
    }

}