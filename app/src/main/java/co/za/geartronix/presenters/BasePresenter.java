package co.za.geartronix.presenters;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.GalleryActivity;
import co.za.geartronix.activities.HomeActivity;
import co.za.geartronix.activities.ProfileActivity;
import co.za.geartronix.activities.ServicesActivity;
import co.za.geartronix.providers.PermissionsProvider;

public abstract class BasePresenter {

    protected String BASE_LOG = "base_log";
    protected String pageTitle;
    public BaseActivity activity;
    public int deviceOrientation;
    protected int clickedViewId;
    private Animation animate;
    protected ActionBar currentActionBar;
    public boolean isBack;
    public String username;
    public String userId;
    protected final String PACKAGENAME = "co.za.geartronix";
    public boolean outOfFocus;

    protected void setDependancies(int contentView) {
        setBasicDependancies(contentView);
        configureActionBar();
    }

    protected void setDependanciesChildActivities(int contentView) {
        setBasicDependancies(contentView);
        configureActionBarChildActivity();
    }

    protected void setDependanciesNoActionBar(int contentView) {
        setBasicDependancies(contentView);
    }

    protected void setBasicDependancies(int contentView) {
        activity.setContentView(contentView);
        setBackgroundImage(contentView);

        if(!isBack)
            slideInActivity();
    }

    protected void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    protected String getPageTitle() {
        return (pageTitle == null || pageTitle.isEmpty()) ? activity.getString(R.string.app_name) : pageTitle;
    }

    protected boolean isTablet() {
        return (activity.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private void setBackgroundImage(int contentView) {

        try {
            View view = (View) activity.findViewById(contentView);
            deviceOrientation = activity.getResources().getConfiguration().orientation;

            if(deviceOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                view.setBackgroundResource(R.drawable.page_background);
            } else {
                view.setBackgroundResource(R.drawable.page_background_portrait);
            }
        }
        catch (Exception e){

        }

    }

    public void configureActionBar() {
        currentActionBar = basicActionBarConfiguration(" "+getPageTitle());
    }

    public void configureActionBarChildActivity() {
        currentActionBar = basicActionBarConfiguration(" "+getPageTitle());
        currentActionBar.setDisplayUseLogoEnabled(false);
        currentActionBar.setDisplayHomeAsUpEnabled(true);
    }


    public void slideInActivity() {
        activity.overridePendingTransition(R.anim.slide_in, R.anim.nothing);
    }

    public void slideInAndFinishActivity() {
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void slideOutActivity() {
        activity.overridePendingTransition(R.anim.nothing, R.anim.slide_out);
    }

    protected Animation getRotationAnimation(long start, long end) {
        animate =  new RotateAnimation(start, end, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animate.setInterpolator(new LinearInterpolator()); //add this
        animate.setDuration(180);

        return  animate;
    }

    protected Animation getfadeInAnimation(long duration) {
        animate = new AlphaAnimation(1, 0);
        animate.setInterpolator(new DecelerateInterpolator()); //add this
        animate.setDuration(duration);

        return  animate;
    }

    protected Animation getfadeOutAnimation(long offset, long duration) {
        animate = new AlphaAnimation(1, 0);
        animate.setInterpolator(new AccelerateInterpolator()); //and this
        animate.setStartOffset(offset);
        animate.setDuration(duration);

        return animate;
    }

    protected Animation getSlideDownAnimation(long height, long duration) {
        animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                height); // toYDelta
        animate.setDuration(duration);
        animate.setFillAfter(false);

        return animate;
    }

    protected void duringAnimation(View view){

    }

    protected void postAnimation(View view){

    }

    protected void blinkView(View view, long from, long to) {

        final View currentActivity = view;

        Animation animate = getfadeOutAnimation(from, to);

        animate.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                duringAnimation(currentActivity);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                postAnimation(currentActivity);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        currentActivity.startAnimation(animate);
    }


    protected ActionBar basicActionBarConfiguration(String title) {
        ActionBar ab = this.activity.getSupportActionBar();
        ab.setTitle(title);

        return ab;
    }

    protected void goToActivity(Class activity, Intent...i) {
        Intent intnt;
        if(i != null && i.length > 0) {
             intnt = i[0];
        }
        else  {
            intnt = new Intent(this.activity, activity);
        }

        this.activity.startActivity(intnt);
    }

    protected void goToActivityWithPayload(Class activity, Bundle...payload) {

        Intent i = new Intent(this.activity, activity);

        if(payload != null && payload.length > 0)
            i.putExtra("payload", payload[0]);

        goToActivity(activity, i);
    }

    protected void goToProfile() {
        goToActivity(ProfileActivity.class);
    }
    protected void goToGallery() {
        goToActivity(GalleryActivity.class);
    }

    protected boolean isCurrentActivity(Class activity) {
        return this.activity.getClass() == activity;
    }

    protected void goToHome(Bundle...loginDetails) {
        if(isCurrentActivity(HomeActivity.class))
            return;

        goToActivityWithPayload(HomeActivity.class, loginDetails);
    }

    protected void goToServices(Bundle...loginDetails) {
        if(isCurrentActivity(ServicesActivity.class))
        return;

        goToActivityWithPayload(ServicesActivity.class, loginDetails);
    }

    protected void goToSettings(Bundle...loginDetails) {
        showShortToast("Settings");
    }

    protected void showLongToast(String message){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }
    protected void showShortToast(String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected Bitmap getImageViewPic(ImageView currImage) {
        Bitmap pic = ((BitmapDrawable)currImage.getDrawable()).getBitmap();
        return  pic;
    }

    protected AlertDialog.Builder setupBasicMessage(String message, String title){

        AlertDialog.Builder ab = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        ab.setMessage(message)
                .setTitle(title)
                .setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onPositiveDialogButtonClicked(dialogInterface,  i);
                    }
                });

        return ab;
    }
    private void showMessage(AlertDialog.Builder ab) {
        AlertDialog a = ab.create();
        a.show();
    }

    protected void showSuccessMessage(String message, String title) {
        AlertDialog.Builder ab = setupBasicMessage(message, title);
        ab.setIcon(R.drawable.success_icon);
        showMessage(ab);
    }

    protected void showErrorMessage(String message, String title) {
        AlertDialog.Builder ab = setupBasicMessage(message, title);
        ab.setIcon(R.drawable.error_icon);
        showMessage(ab);
    }

    protected  void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i){}
    protected  void onNagativeButtonClicked(DialogInterface dialogInterface, int i){}

    protected void requestPermission(String permission) {
        new PermissionsProvider(activity).requestStoragePermission();
    }

    protected boolean isPermissionGranted(String permission) {
       return new PermissionsProvider(activity).checkPermissionGranted(permission);
    }
}
