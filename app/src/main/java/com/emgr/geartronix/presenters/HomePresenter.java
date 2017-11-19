package com.emgr.geartronix.presenters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.GalleryActivity;
import com.emgr.geartronix.activities.HomeActivity;
import com.emgr.geartronix.adapters.HomeTileAdapter;
import com.emgr.geartronix.constants.Constants;
import com.emgr.geartronix.models.AccountModel;
import com.emgr.geartronix.views.IHomeView;
import java.util.ArrayList;
import java.util.List;

public class HomePresenter extends BaseMenuPresenter implements IHomePresenter {

    private AccountModel responseModel;
    private DrawerLayout mDrawerLayout;
    private ImageView selectedActivityImg;
    private GridView homeTileContainer;
    private TextView homeHeaderText;
    private List<ArrayList> homeItems;

    public HomePresenter(IHomeView iHomeView) {

        setDependanciesNoActionBar((BaseActivity) iHomeView, R.layout.activity_home);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.content_home );
        setViews();

        responseModel = new AccountModel();

        try {

            String user = activity.getIntent().getExtras().getBundle("payload").getString("user");
            showShortToast("Welcome "+user);

        }
        catch (Exception e){

        }

    }

    @Override
    public HomeActivity getActivity() {
        return (HomeActivity)activity;
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

    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    public void menuOptionSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_settings:
                showShortToast(getActivity().getString(R.string.settings));
                break;
        }
    }

    @Override
    public void setViews() {
        parentLayout = (FrameLayout) getMainLayout();

        homeHeaderText = (TextView) parentLayout.findViewById(R.id.txtHomeHeader);
        selectedActivityImg = (ImageView)parentLayout.findViewById(R.id.imgSelectedActivity);

        // Set tiles
        homeTileContainer = (GridView) parentLayout.findViewById(R.id.grdHomeTiles);
        homeTileContainer.setAdapter(getAdapter());
    }

    public HomeTileAdapter getAdapter() {

        ArrayList item1 = new ArrayList();
        item1.add(R.mipmap.profile_icon);
        item1.add("Your profile");

        ArrayList item2 = new ArrayList();
        item2.add(R.mipmap.service_icon);
        item2.add("Book a service");

        ArrayList item3 = new ArrayList();
        item3.add(R.mipmap.gallery_icon);
        item3.add("View Gallery");

        ArrayList item4 = new ArrayList();
        item4.add(R.mipmap.find_us_icon);
        item4.add("Find us");

        homeItems = new ArrayList<>();
        homeItems.add(item1);
        homeItems.add(item2);
        homeItems.add(item3);
        homeItems.add(item4);

        return new HomeTileAdapter(getActivity(), R.layout.home_tile_item, homeItems);
    }


private View lastView;

    @Override
    public void handleTileClicked(View view) {
        //setActiveInactiveColor(view);
        animateHomeViews(view);
    }

    private void goToSelectedActivity(View view) {


        switch (view.getId()){

            case Constants.PROFILEID:
                showShortToast("Profile");
            break;
            case Constants.BOOKSERVICEID:
                goToServices();
            break;
            case Constants.GALLERYID:
                goToGallery();
            break;
            case Constants.FINDUSID:
                showShortToast("Find us");
            break;
        }

        getActivity().finish();

    }

//-------------------------------------------------


    private void hideHomeTile(View view) {
        final View currentActivity = (LinearLayout)view;

        Animation animate = getfadeOutAnimation(60, 400);
        animate.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                homeTileContainer.setVisibility(View.INVISIBLE);
                goToSelectedActivity(currentActivity);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        homeTileContainer.startAnimation(animate);
    }

    private void showSelectedActivity() {
        selectedActivityImg.setVisibility(View.VISIBLE);
    }

    private void animateHomeViews(View view) {

        final LinearLayout currentTile = (LinearLayout)view;

        Animation animate = getSlideDownAnimation(view.getHeight(),  400);
        animate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                currentTile.setVisibility(View.INVISIBLE);

                ImageView currentTileImage = (ImageView)currentTile.getChildAt(0);
                Bitmap pic = ((BitmapDrawable)currentTileImage.getDrawable()).getBitmap();
                selectedActivityImg.setImageBitmap(pic);

                TextView currentTileLabel = (TextView)currentTile.getChildAt(1);
                String activityName = currentTileLabel.getText().toString();
                homeHeaderText.setText(activityName);

                showSelectedActivity();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideHomeTile(currentTile);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        homeTileContainer.startAnimation(animate);

    }

    private void setActiveInactiveColor(View view) {
        if(view == lastView)
            return;

        int active = Color.parseColor("#0088cc");
        int inactive = getActivity().getResources().getColor(R.color.tileTransBlack);

        view.setBackgroundColor(active);

        if(lastView != null)
            lastView.setBackgroundColor(inactive);

        lastView = view;
    }

}