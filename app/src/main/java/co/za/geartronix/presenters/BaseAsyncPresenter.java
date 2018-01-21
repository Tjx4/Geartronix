package co.za.geartronix.presenters;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import co.za.geartronix.R;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.GalleryModel;
import co.za.geartronix.providers.HttpConnectionProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAsyncPresenter extends BasePresenter {
    protected boolean isCheckingUpdates;
    protected int httpConTimeout = 6000;
    protected String environment = Constants.CURRENTENVIRONMENT;
    protected List<View> activeButtons  = new ArrayList<>();
    public FrameLayout loadingScreenFrm;

    // Abstract methods
    protected abstract void duringAsyncCall(Integer...values);
    protected abstract Object doAsyncOperation(Object...args) throws Exception;
    protected abstract void handleAsyncButtonClickedEvent(View button);

    protected void checkAndUpdate() {

    }

    protected String getRemoteJson() throws IOException{
        return null;
    }

    protected boolean hasUpdate(GalleryModel remoteGalleryModel) {
        return false;
    }

    protected boolean isCached() {
        return false;
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

    public void onAsyncButtonClickeEvent(View button) {
        if (isCurrentlyWorking(button))
            return;

        activeButtons.add(button);
        //setBusy(true);
        handleAsyncButtonClickedEvent(button);
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
        //setBusy(false);
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
            publishProgress(10);

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