package co.za.geartronix.presenters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.DiagnosticsActivity;
import co.za.geartronix.activities.ForgotPasswordActivity;
import co.za.geartronix.activities.GalleryActivity;
import co.za.geartronix.activities.HomeActivity;
import co.za.geartronix.activities.MessagesActivity;
import co.za.geartronix.activities.ProfileActivity;
import co.za.geartronix.activities.RegistrationActivity;
import co.za.geartronix.activities.ServicesListActivity;
import co.za.geartronix.activities.SettingsActivty;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.fragments.ProfileMoreOptionsFragment;
import co.za.geartronix.providers.DialogFragmentProvider;
import co.za.geartronix.providers.PermissionsProvider;

public abstract class BasePresenter {

    protected String BASE_LOG = "base_log";
    protected String username, pageTitle;
    public BaseActivity activity;
    protected int userId, clickedViewId, deviceOrientation;
    private Animation animate;
    protected ActionBar currentActionBar, ogActionBar;
    public boolean isBack;
    protected final String PACKAGENAME = "co.za.geartronix";
    public boolean outOfFocus, viewOpenState;
    private View lastView;
    protected Menu menuView;

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

    protected void goToFindUs() {
        //goToActivity(FindUsActivity.class);

        String lat = "-26.0800519";
        String lng = "27.92233299999998";
        String label = "Geartronix DSG Centre";

        String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + label + ")";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        activity.startActivity(intent);
    }

    protected void goToMessages(Bundle...extras) {
        if(isCurrentActivity(MessagesActivity.class))
            return;

        goToActivityWithPayload(MessagesActivity.class, extras);
    }

    protected void goToInbox(Bundle...extras) {
        goToMessages(extras);
    }
    protected void goToOutbox(Bundle...extras) {
        goToMessages(extras);
    }

    protected void goToProfile() {
        goToActivity(ProfileActivity.class);
    }
    protected void goToGallery() {
        goToActivity(GalleryActivity.class);
    }
    protected void goToDiscussions() {
        showShortToast("goToDiscussions");
    }

    protected void goToDiagnostics(Bundle...extras) {
        if(isCurrentActivity(DiagnosticsActivity.class))
            return;

        goToActivityWithPayload(DiagnosticsActivity.class, extras);
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
        if(isCurrentActivity(ServicesListActivity.class))
        return;

        goToActivityWithPayload(ServicesListActivity.class, loginDetails);
    }

    protected void goToSettings(Bundle...extras) {
        if(isCurrentActivity(SettingsActivty.class))
            return;

        goToActivityWithPayload(SettingsActivty.class, extras);
    }

    protected void goToForgotPassword(Bundle...extras) {
        if(isCurrentActivity(ForgotPasswordActivity.class))
            return;

        goToActivityWithPayload(ForgotPasswordActivity.class, extras);
    }

    protected void goToRegistration(Bundle...extras) {
        if(isCurrentActivity(RegistrationActivity.class))
            return;

        goToActivityWithPayload(RegistrationActivity.class, extras);
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

    protected AlertDialog.Builder setupBasicMessage(String message, String title, boolean showNagativeButton, boolean showNutralButton){

        AlertDialog.Builder ab = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        ab.setMessage(message)
                .setTitle(title)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onPositiveDialogButtonClicked(dialogInterface,  i);
                    }
                });

        if(showNagativeButton) {
            ab.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onNagativeButtonClicked(dialogInterface, i);
                }
            });
        }

        /*
        if(showNutralButton) {
            ab.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        }
        */

        return ab;
    }
    private void showMessage(AlertDialog.Builder ab) {
        AlertDialog a = ab.create();
        a.show();
        a.getButton(a.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.textWhite));
        a.getButton(a.BUTTON_POSITIVE).setTextColor(activity.getResources().getColor(R.color.textWhite));
        a.getButton(a.BUTTON_NEUTRAL).setTextColor(activity.getResources().getColor(R.color.textWhite));
    }


    protected void showFragmentDialog() {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        // Create and show the dialog.
        ProfileMoreOptionsFragment newFragment = new ProfileMoreOptionsFragment();
        Bundle payload = new Bundle();
        payload.putString(Constants.TITLE, "More options");
        payload.putInt(Constants.LAYOUT, R.layout.profile_more_options_layout);
        newFragment.setArguments(payload);
        newFragment.show(ft, "dialog");

    }


    protected void showSuccessMessage(String message, String title) {
        AlertDialog.Builder ab = setupBasicMessage(message, title, false, false);
        ab.setIcon(R.drawable.success_icon);
        showMessage(ab);
    }

    protected void showErrorMessage(String message, String title) {
        AlertDialog.Builder ab = setupBasicMessage(message, title, false, false);
        ab.setIcon(R.drawable.error_icon);
        showMessage(ab);
    }

    protected void showConfirmMessage(String message, String title, boolean showNagativeButton, boolean showNutralButton) {
        AlertDialog.Builder ab = setupBasicMessage(message, title, showNagativeButton, showNutralButton);
        ab.setIcon(R.drawable.confirm_icon);
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

    protected void resetLastAndSetNew(View view, int defColor, int newColor) {

        if(lastView == view && viewOpenState)
            view.setBackgroundColor(defColor);
        else{
            int activeServiceColor = newColor;
            view.setBackgroundColor(activeServiceColor);
        }

        if(lastView != null && lastView != view)
            lastView.setBackgroundColor(defColor);

        lastView = view;
        viewOpenState = !viewOpenState;
    }

    public void menuOptionSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_settings:
                goToSettings();
                break;
            case R.id.action_help:
                showInstructions();
                break;
        }
    }

    protected void showInstructions() {
        showShortToast("Show page instructions");
    }

    protected void setMenuItemIcon(MenuItem menuItem, int menuItemIcon) {
        menuItem.setIcon(menuItemIcon);
    }

    public void configureActionBarItems(Menu menu) {
        menuView = menu;
    }

    protected void setViewsVisible(View[] views) {
        for(View view : views){
            view.setVisibility(View.VISIBLE);
        }
    }

    protected void setViewsInVisible(View[] views) {
        for(View view : views){
            view.setVisibility(View.GONE);
        }
    }
    protected void setViewHeight(View view, int hieght) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = hieght;
        view.setLayoutParams(lp);
    }
    protected void setViewWidth(View view, int width) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = width;
        view.setLayoutParams(lp);
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected int getScreenHeight() {
        return activity.getWindow().getWindowManager().getDefaultDisplay().getHeight();
    }

    protected int getScreenWidth() {
        return activity.getWindow().getWindowManager().getDefaultDisplay().getWidth();
    }

    public void handleBackButtonPressed(){
        showShortToast("Base handleBackButtonPressed");
    }


    protected void slideUpView(View view) {

        final View currentView = view;

        currentView.animate()
                .translationYBy(-getScreenHeight())
                .setDuration(600)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        currentView.setVisibility(View.GONE);
                    }
                });
    }

    protected void slideDownView(View view) {
        view.setVisibility(View.VISIBLE);

        view.animate()
                .translationYBy(getScreenHeight())
                .setDuration(600)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }
                });
    }


    protected void slideOutView(View view) {

        final View currentView = view;

        currentView.animate()
                .translationXBy(-getScreenWidth())
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        currentView.setVisibility(View.GONE);
                    }
                });
    }

    protected void slideInView(View view) {
        view.setVisibility(View.VISIBLE);

        view.animate()
                .translationXBy(getScreenWidth())
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }
                });
    }

}