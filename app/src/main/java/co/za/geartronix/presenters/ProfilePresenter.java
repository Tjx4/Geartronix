package co.za.geartronix.presenters;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ProfileActivity;
import co.za.geartronix.models.ProfileModel;
import co.za.geartronix.providers.CarProvider;
import co.za.geartronix.models.MemberModel;
import co.za.geartronix.models.MessageModel;
import co.za.geartronix.providers.MockProvider;
import co.za.geartronix.models.ProgressBarModel;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.views.IProfileView;

public class ProfilePresenter extends BaseAppActivityPresenter implements IProfilePresenter{

    private ProfileModel responseModel;
    private UserModel user;
    public boolean isEditMode;
    private ImageButton uploadImageBtn;
    private ProgressBar progressBar1, progressBar2;
    private int points;
    private Bitmap profpic;
    private String city;
    private MemberModel memberType;
    private List<MessageModel> messages;
    private List<CarProvider> cars;
    private ProgressBarModel progressbar1Values, progressbar2Values;
    private TextView usernameTxt, memberTypetxt, cityTxt,pointsCountTxt, messageCountTxt, carsCountTxt;
    private ImageView profpicImg;

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
        else if(clickedViewId == R.id.imgProfpic)
            showPanels(view);

        handleEnlargedImageMethods(view);
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
        if(isEditMode == false)
            blinkView(view, 30, 70);
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

        setLargeImageViews();

        setProfileDetails();
    }

    @Override
    public void setProfileDetails() {
        user = new MockProvider(getActivity()).getMockUser();
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
        ogActionBar = currentActionBar;
        currentActionBar = profileEditActionBar();
        showShortToast(getActivity().getString(R.string.edit_your_profile));
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
    public void setViewMode() {
        uploadImageBtn.setVisibility(View.GONE);
        currentActionBar = ogActionBar;
        showShortToast("Exited Edit mode");
        isEditMode = false;
    }


    @Override
    public void goToCurrentAppActivity() {
        goToProfile();
    }

}