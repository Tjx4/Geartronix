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

    protected void showLongToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    protected void showShortToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    protected void onAsyncButtonClickeEvent(View button) {
        if (isCurrentlyWorking(button))
            return;

        activeButtons.add(button);
        //setBusy(true);
        handleButtonClickedEvent(button);
    }

    private boolean isCurrentlyWorking(View button) {
        return activeButtons.contains(button);
    }

    protected void resetButtonState(View button) {
        activeButtons.remove(button);
        //setBusy(false);
    }
    private void resetIfTriggeredByView(View triggerView) {
        if(triggerView != null)
            resetButtonState(triggerView);
    }

    protected void testConnection() throws IOException {
        String url = Constants.CURRENTENVIRONMENT;
        Bundle payload = new Bundle();
        payload.putString("source", Build.MODEL);

        new HttpConnectionProvider(payload).makeCallForData(url, "POST", true, true, httpConTimeout);
    }

    // Abstract methods
    protected abstract void beforeAsyncCall();
    protected abstract void duringAsyncCall(Integer...values);
    protected abstract Object doAsyncOperation(Object...args) throws Exception;
    protected abstract void afterAsyncCall(Object result);
    protected abstract void handleButtonClickedEvent(View button);

    public class DoAsyncCall extends AsyncTask<Object, Integer, Object> {

        private View triggerView;

        public DoAsyncCall(View...triggerViews) {
            if(triggerViews != null)
                this.triggerView = triggerViews[0];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            beforeAsyncCall();
        }

        @Override
        protected void onProgressUpdate(Integer...values) {
            super.onProgressUpdate(values);
            duringAsyncCall(values);
        }

        @Override
        protected Object doInBackground(Object...args) {

            Object res;

            try {
                res = doAsyncOperation(args);
            }
            catch (IOException e){
                res = "Error! "+e;
            }
            catch (InterruptedException e){
                res = "Error! "+e;
            } catch (Exception e) {
                res = "Error! "+e;
            }

            return res;
        }

        @Override
        protected void onPostExecute(Object outputData) {
            super.onPostExecute(outputData);
            afterAsyncCall(outputData);

            resetIfTriggeredByView(triggerView);
        }

    }

}