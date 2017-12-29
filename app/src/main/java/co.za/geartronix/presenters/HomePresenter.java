package co.za.geartronix.presenters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.HomeActivity;
import co.za.geartronix.adapters.HomeTileAdapter;
import co.za.geartronix.models.AccountModel;
import co.za.geartronix.views.IHomeView;
import java.util.ArrayList;
import java.util.List;

public class HomePresenter extends BaseMenuPresenter implements IHomePresenter {

    private AccountModel responseModel;
    private DrawerLayout mDrawerLayout;
    private ImageView selectedActivityImg;
    private GridView homeTileContainer;
    private TextView homeHeaderText;
    private List<BaseAppActivityPresenter> homeItems;
    private LinearLayout currentTile;
    private View lastView;
    private HomeTileAdapter homeTileAdapter;

    public HomePresenter(IHomeView iHomeView) {
        super((BaseActivity)iHomeView);
        setDependanciesNoActionBar(R.layout.activity_home);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.content_home);
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
    public void setViews() {
        parentLayout = (RelativeLayout) getMainLayout();

        homeHeaderText = (TextView) parentLayout.findViewById(R.id.txtHomeHeader);
        selectedActivityImg = (ImageView)parentLayout.findViewById(R.id.imgSelectedActivity);

        // Set tiles
        homeTileContainer = (GridView) parentLayout.findViewById(R.id.grdHomeTiles);
        homeTileContainer.setAdapter(getAdapter());
    }

    public HomeTileAdapter getAdapter() {

        homeItems = new ArrayList<>();
        homeItems.add(new ProfilePresenter(activity, 0));
        homeItems.add(new ServicesPresenter(activity, 1));
        homeItems.add(new GalleryPresenter(activity, 2));
        homeItems.add(new FindUsPresenter(activity, 3));
        homeItems.add(new MessagesPresenter(activity, 4));
        homeItems.add(new DiagnosticsPresenter(activity, 5));

        //Todo: fix
        homeTileAdapter = new HomeTileAdapter(getActivity(), R.layout.home_tile_item, homeItems);

        return homeTileAdapter;
    }

    @Override
    public void handleTileClicked(View view) {
        //setActiveInactiveColor(view);
        //animateHomeViews(view);
       // animateAndGotoActivity(view);
        blinkTile(view);
    }

    private void goToSelectedActivity(View view) {
      BaseAppActivityPresenter currentAppActivity = homeTileAdapter.generateAppActivity(view.getId());
      currentAppActivity.goToCurrentAppActivity();
    }

    private void hideHomeTile(View view) {
        final View currentActivity = view;

        Animation animate = getfadeOutAnimation(60, 400);
        animate.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                homeTileContainer.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
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

    public void resetHomeTitle() {
        homeHeaderText.setText(getActivity().getString(R.string.choose_an_activity));
    }

    private void animateAndGotoActivity(View view) {
        /*
        currentTile = (LinearLayout)view;

        setActiveInactiveColor(currentTile);
        goToSelectedActivity(currentTile);

        final View currentActivity = view;

        Animation animate = getfadeOutAnimation(60, 400);
        animate.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                homeTileContainer.setVisibility(View.INVISIBLE);

                ImageView currentTileImage = (ImageView)currentTile.getChildAt(0);
                selectedActivityImg.setImageBitmap(getImageViewPic(currentTileImage));

                TextView currentTileLabel = (TextView)currentTile.getChildAt(1);
                String activityName = currentTileLabel.getText().toString();
                homeHeaderText.setText(activityName);
                //
                homeHeaderText.setVisibility(View.VISIBLE);

                //This is where you animate the selected activity
                showSelectedActivity();

            }

            @Override
            public void onAnimationEnd(Animation animation) {

// Todo: find better way to delay
                Thread lTimer = new Thread() {

                    public void run() {

                        Looper.prepare();

                        try {
                            Thread.sleep(200);
                            goToSelectedActivity(currentActivity);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                lTimer.start();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        homeTileContainer.startAnimation(animate);
        */
    }

    private void animateHomeViews(View view) {
/*
        //view.getHeight()
        Animation animate = getSlideDownAnimation(500,  600);
        animate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                currentTile.setVisibility(View.INVISIBLE);

                ImageView currentTileImage = (ImageView)currentTile.getChildAt(0);
                selectedActivityImg.setImageBitmap(getImageViewPic(currentTileImage));

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
*/
    }

    public void resetTiles() {

        int visible = View.VISIBLE;
        int invisible = View.INVISIBLE;

        selectedActivityImg.setVisibility(invisible);
        homeHeaderText.setVisibility(View.GONE);
        resetHomeTitle();

        //if(homeTileContainer.getVisibility() != visible)
            homeTileContainer.setVisibility(visible);

        //if(currentTile != null)
        //    currentTile.setVisibility(visible);
    }

    private void setActiveInactiveColor(View view) {

        setActiveColor(view);

        if(view == lastView)
            return;

        if(lastView != null)
            revertViewBackgroundColor(lastView);

        lastView = view;
    }

    private void setActiveColor(View view) {
        int active = Color.parseColor("#0088cc");
        view.setBackgroundColor(active);
    }

    private void revertViewBackgroundColor(View lastView) {
        int inactive = getActivity().getResources().getColor(R.color.tileTransBlack);
        lastView.setBackgroundColor(inactive);
    }

    private void blinkTile(View view) {

        final View currentActivity = view;

        Animation animate = getfadeOutAnimation(30, 70) ;

        animate.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                setActiveInactiveColor(currentActivity);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                revertViewBackgroundColor(currentActivity);
                goToSelectedActivity(currentActivity);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        currentActivity.startAnimation(animate);
    }

// Todo: work on this method
    private void slideInAndOutView(View view, boolean isTwice) {

        /*
        final Boolean secondTime = isTwice;
        final View currentActivity = view;
        int duration = 200;

        Animation animate;

        if (isTwice) {
            animate = getfadeInAnimation(duration);
        }
        else {
            animate = getfadeOutAnimation(30, duration) ;
        }

        animate.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(secondTime)
                    goToSelectedActivity(currentActivity);
                else
                    slideInAndOutView(currentActivity, true);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        currentActivity.startAnimation(animate);
        */
    }

    @Override
    public void handleViewClickedEvent(View view) {

    }

}