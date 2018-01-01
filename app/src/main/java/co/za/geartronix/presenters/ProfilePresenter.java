package co.za.geartronix.presenters;

import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ProfileActivity;
import co.za.geartronix.models.ProfileModel;
import co.za.geartronix.views.IProfileView;

public class ProfilePresenter extends BaseAppActivityPresenter implements IProfilePresenter{

    private ProfileModel responseModel;
    public boolean isEditMode;

    public ProfilePresenter(IProfileView iProfileView) {
        super((BaseActivity)iProfileView);
        setDependanciesChildActivities(R.layout.activity_profile);
        currentActionBar.setTitle(" "+activity.getString(R.string.profile));
        setViews();
        responseModel = new ProfileModel();
        new DoAsyncCall().execute();
    }

    public ProfilePresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.profile_icon);
        setDisplayName(activity.getString(R.string.profile));
    }

    @Override
    protected void beforeAsyncCall() {

    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        try {
            String res = result.toString();

            responseModel.setModel(new JSONObject(res));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        hideLoadingScreen();
    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    public void handleViewClickedEvent(View view) {

    }

    @Override
    public void setViews() {

    }

    @Override
    public ProfileActivity getActivity() {
        return (ProfileActivity) activity;
    }

    @Override
    public void addCar(View view) {

    }

    @Override
    public void uploadPicture(View view) {

    }

    @Override
    public void viewMessages(View view) {

    }

    @Override
    public void viewPoints(View view) {
    }

    @Override
    public void setEditMode() {
        isEditMode = true;
        //
    }

    @Override
    public void setViewMode() {
        isEditMode = false;
    }

    @Override
    public void goToCurrentAppActivity() {
        goToProfile();
    }
}