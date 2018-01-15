package co.za.geartronix.presenters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ProfileActivity;
import co.za.geartronix.adapters.CarsAdapter;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.NamesModel;
import co.za.geartronix.models.ProfileModel;
import co.za.geartronix.models.CarModel;
import co.za.geartronix.models.MemberModel;
import co.za.geartronix.models.MessageModel;
import co.za.geartronix.providers.MessagesCategoryProvider;
import co.za.geartronix.providers.MockProvider;
import co.za.geartronix.models.ProgressBarModel;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.views.IProfileView;

public class ProfilePresenter extends BaseAppActivityPresenter implements IProfilePresenter{

    private ProfileModel responseModel;
    private UserModel user;
    public boolean isEditMode, isCarVieOptend;
    private ImageButton uploadImageBtn;
    private ProgressBar progressBar1, progressBar2;
    private int points;
    private Bitmap profpic;
    private String city, newUsername, newCity;
    private MemberModel memberType;
    private List<MessageModel> messages;
    private List<CarModel> cars;
    private ListView carsLst;
    private ProgressBarModel progressbar1Values, progressbar2Values;
    private TextView usernameTxt, memberTypetxt, cityTxt,pointsCountTxt, messageCountTxt, carsCountTxt, editUsernameTxt, editCityTxt;
    private ImageView profpicImg;
    private ImageView moreBtn, saveBtn;
    private MenuItem settingsMenuItem, modeMenuItem, saveMenuItem;
    private FrameLayout overLayfrm, overLayfrm2;
    private RelativeLayout carViewContainerRltv;
    private Fragment carViewContainerFrag;
    private RelativeLayout userInfoRltv;
    private View profContainerFrm;
    private DialogFragment dialogFragment;
    private ImageButton closeCarViewBtn;

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
        else if(clickedViewId == R.id.btnMore)
            showMoreOptions(view);
        else if(clickedViewId == R.id.lnrViewCars)
            viewCars(view);
        else if(clickedViewId == R.id.lnrViewMessages)
            viewMessages(view);
        else if(clickedViewId == R.id.lnrViewPoints)
            viewPoints(view);
        else if(clickedViewId == R.id.imgProfpic)
            showPanels(view);
        else if(clickedViewId == R.id.itemViewCars)
            viewCars(view);
        else if(clickedViewId == R.id.itemEdtitProfile)
            setEditMode();
        else if(clickedViewId == R.id.itemViewMessages)
            viewMessages(view);
        else if(clickedViewId == R.id.btnSave)
            checkAndSave();
        else if(clickedViewId == R.id.btnCloseCarView)
            closeCarView(false);

        if(dialogFragment != null)
            dialogFragment.dismiss();

        handleEnlargedImageMethods(view);
    }

    @Override
    public void checkAndSave() {

        if(isProfileChanged()) {
            saveChanges();
        }
        else {
            if(hasEmpty()) {
                showShortToast("No empty field are permited");
            }
            else {
                showShortToast(getActivity().getString(R.string.profile_update_error_message));
            }
        }
    }


    @Override
    public void handleBackButtonPressed(){
        hideKeyboard();
        if(isProfileChanged())
            confirmChanges();
        else
            setViewMode();

    }

    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);
        hideKeyboard();

        clickedViewId = item.getItemId();
        boolean isSettings = clickedViewId == R.id.action_settings;
        boolean isEdit = clickedViewId == R.id.action_edit;
        boolean isSave = clickedViewId == R.id.action_save;

        if(isSettings)
            goToSettings();
        else if(isEdit)
            toggleModes();
        else if(isSave)
            checkAndSave();

        if(isCarVieOptend)
            closeCarView(isSettings);
    }


    @Override
    public void toggleModes() {
        if(isEditMode) {
            if(isProfileChanged())
                confirmChanges();
            else
                setViewMode();
        }
        else{
            if(imageEnlarged)
                hideEnlargedImage();

            setEditMode();
        }
    }

    @Override
    public void handleViewClickedEvent(View view) {
        int viewId = view.getId();
        boolean isUploadPropic = viewId == R.id.imgBtnuploadImage;
        boolean isSaveUpdate = viewId == R.id.btnSave;
        boolean viewMode = !isEditMode;

        if(viewId == R.id.lnrServiceContainer)
            toggleSubContent(view);
        else if(viewMode || isUploadPropic || isSaveUpdate)
            blinkView(view, 30, 70);

    }


    public void handleFragmentClickedEvent(DialogFragment dialogFragment, View view) {
        handleViewClickedEvent(view);
        this.dialogFragment = dialogFragment;
    }

    @Override
    public void configureActionBarItems(Menu menu) {
        super.configureActionBarItems(menu);
        settingsMenuItem = menuView.getItem(0);
        modeMenuItem = menuView.getItem(1);
        saveMenuItem = menuView.getItem(3);
    }

    @Override
    public void setViews() {
        uploadImageBtn = (ImageButton) getActivity().findViewById(R.id.imgBtnuploadImage);
        progressBar1 = (ProgressBar) getActivity().findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) getActivity().findViewById(R.id.progressBar2);
        pointsCountTxt = (TextView) getActivity().findViewById(R.id.txtPointsCount);
        messageCountTxt = (TextView) getActivity().findViewById(R.id.txtMessageCount);
        carsCountTxt = (TextView) getActivity().findViewById(R.id.txtCarsCount);
        usernameTxt = (TextView) getActivity().findViewById(R.id.txtUserName);
        memberTypetxt = (TextView) getActivity().findViewById(R.id.txtMemberType);
        cityTxt = (TextView) getActivity().findViewById(R.id.txtCity);
        profpicImg = (ImageView) getActivity().findViewById(R.id.imgProfpic);
        editUsernameTxt = (EditText) getActivity().findViewById(R.id.txtEditUsername);
        editCityTxt = (EditText) getActivity().findViewById(R.id.txtEditCity);
        overLayfrm = (FrameLayout) getActivity().findViewById(R.id.frmOverlay1);
        overLayfrm2 = (FrameLayout) getActivity().findViewById(R.id.frmOverlay2);
        carsLst = (ListView) getActivity().findViewById(R.id.lstCars);
        moreBtn = (ImageButton) getActivity().findViewById(R.id.btnMore);
        saveBtn = (ImageButton) getActivity().findViewById(R.id.btnSave);
        carViewContainerRltv = (RelativeLayout) getActivity().findViewById(R.id.rltvCarViewContainer);
        //closeCarViewBtn = (ImageButton) getActivity().findViewById(R.id.btnCloseCarView);
        userInfoRltv = (RelativeLayout) getActivity().findViewById(R.id.rltvUserInfo);
        profContainerFrm = getActivity().findViewById(R.id.frmProfPicContainer);

        overLayfrm.animate().alpha(0.0f).setDuration(imageAnimationDuration);
        overLayfrm2.animate().alpha(0.0f).setDuration(imageAnimationDuration);
        closeCarView(false);

        UserModel user = new MockProvider(getActivity()).getMockUser();
        setProfileDetails(user);

        setLargeImageViews();
    }

    @Override
    public void setProfileDetails(UserModel user) {
        username = user.getNames().getFirstName();
        memberType = user.getMemberType();
        profpic = user.getProfilePic();
        city = user.getCity();
        userId = user.getId();
        cars = user.getCars();
        points = user.getPoints();
        messages = user.getMessages();
        progressbar1Values = user.getProgressBar1();
        progressbar2Values  = user.getProgressBar2();

        setUsername(username);
        setProfPic(profpic);
        setMemberType(memberType.getSimpleName());
        setCity(city);
        setMessageCount(messages.size());
        setPointsCount(points);
        setCarsCount(cars.size());
        setProgressbar1Progress(progressbar1Values);
        setProgressbar2Progress(progressbar2Values);

        this.user = user;
    }

     public void setProgressbar1Progress(ProgressBarModel progressBarValues) {
        setProgressbarProgress(progressBar1, progressBarValues);
     }

     public void setProgressbar2Progress(ProgressBarModel progressBarValues) {
        setProgressbarProgress(progressBar2, progressBarValues);
     }

     public void setProgressbarProgress(ProgressBar progressBar, ProgressBarModel progressBarValues) {

         final ProgressBarModel values = progressBarValues;

         FrameLayout parentView =  (FrameLayout)progressBar.getParent();
         LinearLayout sibling = (LinearLayout)parentView.getChildAt(2);
         final TextView txtInfo1 = (TextView) sibling.getChildAt(0);
         final TextView txtInfo2 = (TextView) sibling.getChildAt(1);

         int progress = (int)progressBarValues.getBarValueA();
         ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, progress);
         animation.setDuration (2000);
         animation.setInterpolator (new DecelerateInterpolator());
         animation.addListener(new Animator.AnimatorListener() {
             @Override
             public void onAnimationStart(Animator animation) {
             }

             @Override
             public void onAnimationEnd(Animator animation) {
                 txtInfo1.setText(values.getBarValueA()+"");
                 txtInfo2.setText(values.getBarValueB()+"");
             }

             @Override
             public void onAnimationCancel(Animator animation) {
             }

             @Override
             public void onAnimationRepeat(Animator animation) {

             }
         });
         animation.start ();
     }

    @Override
    public ProfileActivity getActivity() {
        return (ProfileActivity) activity;
    }

    @Override
    public boolean hasEmpty() {
        return newUsername.isEmpty() || newCity.isEmpty();
    }

    @Override
    public boolean isProfileChanged() {
        newUsername = editUsernameTxt.getText().toString().trim();
        newCity = editCityTxt.getText().toString().trim();

        if(hasEmpty())
            return false;

        boolean usernameChanged = !newUsername.equals(username);
        boolean cityChanged = !newCity.equals(city);

        return usernameChanged || cityChanged;
    }

    @Override
    public void addCar(View view) {
        showShortToast("Add new car");
        // Start activity for result = Add a car
    }

    @Override
    public void updateProfile() {

        UserModel user = new MockProvider(getActivity()).getMockUser();

        NamesModel namesModel = new NamesModel();
        namesModel.setFirstName(newUsername);
        user.setNames(namesModel);

        user.setCity(newCity);

        setProfileDetails(user);

        showShortToast(getActivity().getString(R.string.profile_update_success_message));
    }

    @Override
    public void bookAService(View view) {
        goToServices();
    }

    @Override
    public void askQuestion(View view) {
            showShortToast("Ask question");
        // show Ask a question dialog
    }

    @Override
    public void uploadPicture(View view) {
        showShortToast("uploadPicture");
        // show dialog fragment with option to use camera or upload from device
    }

    @Override
    public void viewMessages(View view) {
        Bundle extras = new Bundle();
        extras.putInt(Constants.TYPEID, MessagesCategoryProvider.inbox.getId());
        goToInbox(extras);
    }

    @Override
    public void viewPoints(View view) {
        showShortToast("viewPoints");
        // Just show a dialog that tells the user how much points they have and what they need to do to get more
    }

    @Override
    public void showMoreOptions(View view) {
        // show dialog with list with categories (Car:Add new car, View cars) (Message: view messages, write message)
        showFragmentDialog();
    }

    @Override
    public void viewCars(View view) {

        if(carsLst.getAdapter() == null){
            CarsAdapter carsAdapter = new CarsAdapter(getActivity(), R.layout.car_item,cars);
            carsLst.setAdapter(carsAdapter);
        }

        slideInView(carViewContainerRltv);
        isCarVieOptend = true;
    }

    @Override
    public void closeCarView(boolean justHide) {

        if(justHide)
            carViewContainerRltv.setVisibility(View.GONE);

            slideOutView(carViewContainerRltv);

        isCarVieOptend = false;
    }

    @Override
    public void setEditMode() {
        setViewsVisible(new View[]{uploadImageBtn, editUsernameTxt, editCityTxt, overLayfrm, overLayfrm2});
        fadeInOverlay(overLayfrm);
        fadeInOverlay(overLayfrm2);
        setViewsInVisible(new View[]{usernameTxt, cityTxt, memberTypetxt, moreBtn});
        editUsernameTxt.setText(username);
        editCityTxt.setText(city);
        ogActionBar = currentActionBar;
        currentActionBar.setTitle("Edit"); //= profileEditActionBar();
        saveMenuItem.setVisible(true);
        settingsMenuItem.setVisible(false);
        setMenuItemIcon(modeMenuItem, R.drawable.cancell_icon);
        setViewHeight(userInfoRltv , userInfoRltv.getHeight() + 25);
        profContainerFrm.animate().setDuration(200).translationYBy(-30).start();
        isEditMode = true;
        //showShortToast(getActivity().getString(R.string.edit_your_profile));
    }

    @Override
    public void setViewMode() {
        setViewsVisible(new View[]{usernameTxt, cityTxt, memberTypetxt, moreBtn});
        fadeOutOverlay(overLayfrm);
        fadeOutOverlay(overLayfrm2);
        setViewsInVisible(new View[]{uploadImageBtn, editUsernameTxt, editCityTxt});
        currentActionBar.setTitle(R.string.profile);
        saveMenuItem.setVisible(false);
        settingsMenuItem.setVisible(true);
        setMenuItemIcon(modeMenuItem, R.drawable.edit_icon);
        setViewHeight(userInfoRltv , RelativeLayout.LayoutParams.WRAP_CONTENT);
        profContainerFrm.animate().setDuration(200).translationYBy(30).start();
        isEditMode = false;
        //showShortToast(getActivity().getString(R.string.profile_editmode_exited));
    }

    @Override
    public MenuItem getModeMenuItem() {
        return modeMenuItem;
    }

    @Override
    public void setMessageCount(int count) {
        messageCountTxt.setText(count+"");
    }

    @Override
    public void setCarsCount(int count) {
        carsCountTxt.setText(count+"");
    }

    @Override
    protected void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i) {
        setViewMode();
        updateProfile();
    }

    @Override
    protected void onNagativeButtonClicked(DialogInterface dialogInterface, int i) {
        setViewMode();
        showShortToast("Changes not applied.");
    }

    @Override
    public void confirmChanges() {
        showConfirmMessage(getActivity().getString(R.string.profile_change_confirmation_message), getActivity().getString(R.string.confirm), true, false);
    }

    @Override
    public void saveChanges() {
        setViewMode();
        updateProfile();
    }

    @Override
    public void setPointsCount(int count) {
        pointsCountTxt.setText(count+"");
    }

    @Override
    public void setUsername(String username) {
        usernameTxt.setText(username);
    }

    @Override
    public void setMemberType(String memberType) {
        memberTypetxt.setText(memberType);
    }

    @Override
    public void setCity(String city) {
        cityTxt.setText(city);
    }

    @Override
    public void setProfPic(Bitmap image) {
        profpicImg.setImageBitmap(image);
    }

    @Override
    public ActionBar profileEditActionBar() {
        ActionBar me = this.activity.getSupportActionBar();
        me.setTitle("Save");
        return  me;
    }

    @Override
    public void goToCurrentAppActivity() {
        goToProfile();
    }

}