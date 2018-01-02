package co.za.geartronix.presenters;

import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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
    public ImageButton uploadImageBtn;

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
        return "";
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
    protected void duringAnimation(View view) {

    }

    @Override
    protected void postAnimation(View view) {
        clickedViewId = view.getId();

        if(clickedViewId == R.id.rltvAddnewCar)
            addCar(view);
        else if(clickedViewId == R.id.rltvbookAService)
            bookAService(view);
        else if(clickedViewId == R.id.rltvAskAquestion)
            askQuestion(view);
        else if(clickedViewId == R.id.imgBtnuploadImage)
            uploadPicture(view);
        else if(clickedViewId == R.id.imgMore)
            showMoreOptions(view);
        else if(clickedViewId == R.id.lnrViewCars)
            viewCars(view);
        else if(clickedViewId == R.id.lnrViewMessages)
            viewMessages(view);
        else if(clickedViewId == R.id.lnrViewPoints)
            viewPoints(view);
    }

    public void menuOptionSelected(MenuItem item) {
        clickedViewId = item.getItemId();

        if(clickedViewId == R.id.action_settings)
            goToSettings();
        else  if(clickedViewId == R.id.action_edit)
            setEditMode();
    }

    @Override
    public void handleViewClickedEvent(View view) {
            blinkView(view, 30, 70);
    }

    @Override
    public void setViews() {
        uploadImageBtn = (ImageButton) getActivity().findViewById(R.id.imgBtnuploadImage);
    }

    @Override
    public ProfileActivity getActivity() {
        return (ProfileActivity) activity;
    }

    @Override
    public void addCar(View view) {
        showShortToast("Add new car");
    }

    @Override
    public void bookAService(View view) {
            showShortToast("Book a service");
    }

    @Override
    public void askQuestion(View view) {
            showShortToast("Ask question");
    }

    @Override
    public void uploadPicture(View view) {
        showShortToast("uploadPicture");
    }

    @Override
    public void viewMessages(View view) {
        showShortToast("viewMessages");
    }

    @Override
    public void viewPoints(View view) {
        showShortToast("viewPoints");
    }

    @Override
    public void showMoreOptions(View view) {
        showShortToast("showMoreOptions");
    }

    @Override
    public void viewCars(View view) {
        showShortToast("view cars");
    }

    @Override
    public void setEditMode() {
        isEditMode = true;
        uploadImageBtn.setVisibility(View.VISIBLE);
        currentActionBar = profileEditActionBar();
        showShortToast(activity.getString(R.string.edit));
    }


    private ActionBar profileEditActionBar() {
        ActionBar me = this.activity.getSupportActionBar();
        me.setTitle("Save");
        return  me;
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