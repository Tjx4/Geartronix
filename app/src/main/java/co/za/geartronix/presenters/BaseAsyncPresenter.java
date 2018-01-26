package co.za.geartronix.presenters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.BaseModel;
import co.za.geartronix.models.GalleryModel;
import co.za.geartronix.providers.HttpConnectionProvider;

public abstract class BaseAsyncPresenter extends BasePresenter{

    protected boolean isCheckingUpdates;
    protected int httpConTimeout = 6000;
    protected String environment = Constants.CURRENTENVIRONMENT;
    protected List<View> activeButtons  = new ArrayList<>();
    public FrameLayout loadingScreenFrm;
    protected TextView laodingTxt;
    protected ProgressBar loadingProgressBar;
    protected ImageView loadingImg;
    protected int actionIndex;

    protected abstract Object doAsyncOperation(int actionIndex) throws Exception;

    public boolean handleNavigationItemSelected(MenuItem item) {
        return false;
    }

    public void handleBackButtonPressed(){

    }

    protected boolean isNullModel(BaseModel model) {
        return model == null;
    }

    protected void checkAndUpdate() {

    }

    protected String getRemoteJson(int methodIndex) throws IOException, JSONException {
        return null;
    }

    protected boolean hasUpdate(GalleryModel remoteGalleryModel) {
        return false;
    }

    protected boolean isCached() {
        return false;
    }

    public void handleAsyncButtonClickedEvent(View view) {
        if (isCurrentlyWorking(view))
            return;

        activeButtons.add(view);
    }

    protected void beforeAsyncCall(){
        showLoadingScreen();
        hideKeyboard();
    }


    protected void duringAsyncCall(Integer...values){

    }

    protected void afterAsyncCall(Object result){
        hideLoadingScreen();
    }

    protected void setAsyncViews() {
        loadingScreenFrm = (FrameLayout) activity.findViewById(R.id.frmLoadingScreen);
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

    protected void hideLoadingProgressBar() {
        loadingProgressBar.setVisibility(View.GONE);
    }
    protected void showLoadingSuccessTic() {
        loadingImg.setVisibility(View.VISIBLE);
    }


    protected void showPostAsyncSuccess() {
        hideLoadingProgressBar();
        setLoadingText("");
        showLoadingSuccessTic();
    }

    protected void setLoadingText(String loadingText) {
        laodingTxt.setText(loadingText);
    }

    protected void showLoadingScreen() {

    }
    protected void hideLoadingScreen() {

    }

    protected void testConnection() throws IOException {
        String url = Constants.CURRENTENVIRONMENT;
        Bundle payload = new Bundle();
        payload.putString("source", Build.MODEL);

        new HttpConnectionProvider(payload).makeCallForData(url, "POST", true, true, httpConTimeout);
    }

    public class DoAsyncCall extends AsyncTask<Integer, Integer, Object> {

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
        protected Object doInBackground(Integer...actionIndex) {
            Object res;

            try {
                res = doAsyncOperation(actionIndex[0]);
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