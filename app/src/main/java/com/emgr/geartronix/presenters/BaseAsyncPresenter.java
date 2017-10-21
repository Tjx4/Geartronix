package com.emgr.geartronix.presenters;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.emgr.geartronix.constants.Constants;
import com.emgr.geartronix.providers.HttpConnectionProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAsyncPresenter extends BasePresenter{
    protected int httpConTimeout = 6000;
    protected String environment = Constants.CURRENTENVIRONMENT;
    protected List<View> activeButtons  = new ArrayList<>();

    // Abstract methods
    protected abstract void beforeAsyncCall();
    protected abstract void duringAsyncCall(Integer...values);
    protected abstract Object doAsyncOperation(Object...args) throws Exception;
    protected abstract void afterAsyncCall(Object result);
    protected abstract void handleAsyncButtonClickedEvent(View button);


    public void onAsyncButtonClickeEvent(View button) {
        if (isCurrentlyWorking(button))
            return;

        activeButtons.add(button);
        //setBusy(true);
        handleAsyncButtonClickedEvent(button);
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

            if(appDisposed())
                return;

            super.onPostExecute(outputData);
            afterAsyncCall(outputData);

            resetIfTriggeredByView(triggerView);
        }

        private boolean appDisposed() {
            return activity == null;
        }

    }
}
