package co.za.geartronix.presenters;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.GalleryModel;
import co.za.geartronix.providers.HttpConnectionProvider;

public abstract class BaseAsyncPresenter extends BasePresenter{

    protected boolean isCheckingUpdates;
    protected int httpConTimeout = 6000;
    protected String environment = Constants.CURRENTENVIRONMENT;
    protected List<View> activeButtons  = new ArrayList<>();
    public FrameLayout loadingScreenFrm;

    protected abstract void duringAsyncCall(Integer...values);
    protected abstract Object doAsyncOperation(Object...args) throws Exception;

    public boolean handleNavigationItemSelected(MenuItem item) {
        return false;
    }

    public void handleBackButtonPressed(){

    }

    protected void checkAndUpdate() {

    }

    protected String getRemoteJson() throws IOException {
        return null;
    }

    protected boolean hasUpdate(GalleryModel remoteGalleryModel) {
        return false;
    }

    protected boolean isCached() {
        return false;
    }

    public void handleAsyncButtonClickedEvent(View button) {
        if (isCurrentlyWorking(button))
            return;

        activeButtons.add(button);
    }

    protected void beforeAsyncCall(){
        showLoadingScreen();
        hideKeyboard();
    }

    protected void afterAsyncCall(Object result){
        hideLoadingScreen();
    }

    protected void setAsyncViews() {
        loadingScreenFrm = (FrameLayout) activity.findViewById(R.id.frmLoadingScreen);
    }

    protected void showLoadingScreen() {
        if(loadingScreenFrm != null)
            loadingScreenFrm.setVisibility(View.VISIBLE);
    }
    protected void hideLoadingScreen() {
        if(loadingScreenFrm != null)
            loadingScreenFrm.setVisibility(View.GONE);
    }

    private boolean isCurrentlyWorking(View button) {
        return activeButtons.contains(button);
    }

    protected void resetButtonState(View button) {
        activeButtons.remove(button);
    }
    protected void resetIfTriggeredByView(View triggerView) {
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
            if(triggerViews != null && triggerViews.length > 0)
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
            return activity.presenter == null;
        }
    }
}