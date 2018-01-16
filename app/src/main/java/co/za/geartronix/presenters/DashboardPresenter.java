package co.za.geartronix.presenters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.DashBoardActivity;
import co.za.geartronix.adapters.HomeTileAdapter;
import co.za.geartronix.models.AccountModel;
import co.za.geartronix.views.IHomeView;
import java.util.ArrayList;
import java.util.List;

public class DashboardPresenter extends BaseMenuPresenter implements IDashboardPresenter {

    private AccountModel responseModel;
    private DrawerLayout mDrawerLayout;
    private ImageView welcomeImg;
    private GridView homeTileContainer;
    private TextView homeHeaderText;
    private List<BaseAppActivityPresenter> homeItems;
    private View lastView;
    private HomeTileAdapter homeTileAdapter;

    public DashboardPresenter(IHomeView iHomeView) {
        super((BaseActivity)iHomeView);
        setDependanciesNoActionBar(R.layout.activity_home);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.content_home);
        setViews();

        responseModel = new AccountModel();
    }

    @Override
    public DashBoardActivity getActivity() {
        return (DashBoardActivity)activity;
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
        parentLayout = getMainLayout();
        homeHeaderText = parentLayout.findViewById(R.id.txtHomeHeader);
        welcomeImg = parentLayout.findViewById(R.id.imgWelcome);


        // Set tiles
        homeTileContainer = parentLayout.findViewById(R.id.grdHomeTiles);
        homeTileContainer.setAdapter(getAdapter());

        homeTileContainer.setVisibility(View.GONE);

        slideInTiles();

    }

    public void showWelcome() {

        welcomeImg.setVisibility(View.VISIBLE);

        welcomeImg.animate()
                .alpha(1.0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        welcomeImg.animate()
                                .alpha(0.0f)
                                .setDuration(900)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        slideInTiles();
                                    }
                                });
                    }
                });
    }

    @Override
    public void slideInTiles() {

        homeTileContainer.animate()
                .translationXBy(-setHorizontalSlideLength(horizontalSlideWidth))
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        homeTileContainer.setVisibility(View.VISIBLE);

                        homeTileContainer.animate()
                                .translationXBy(setHorizontalSlideLength(horizontalSlideWidth))
                                .setDuration(800)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        greetUser();
                                    }
                                });
                    }
                });
    }

    @Override
    public void greetUser() {

        try {
            String user = getActivity().getIntent().getExtras().getBundle("payload").getString("user");
            showShortToast(activity.getString(R.string.welcome)+" "+user);
        }
        catch (Exception e) {

        }
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

    private void goToSelectedActivity(View view) {
      BaseAppActivityPresenter currentAppActivity = homeTileAdapter.generateAppActivity(view.getId());
      currentAppActivity.goToCurrentAppActivity();
    }

    private void setActiveInactiveColor(View view) {

        setActiveColor(view);

        if (view == lastView)
            return;

        if (lastView != null)
            revertViewBackgroundColor(lastView);

        lastView = view;
    }

    int ogColor;

    @Override
    public void setActiveColor(View view) {
        ogColor = ((ColorDrawable) view.getBackground()).getColor();
        int active = getActivity().getResources().getColor(R.color.transBlack); //Color.parseColor("#0088cc");
        view.setBackgroundColor(active);
    }

    @Override
    public void revertViewBackgroundColor(View lastView) {
        int inactive = ogColor;
        lastView.setBackgroundColor(inactive);
    }

    @Override
    protected void duringAnimation(View view) {
        setActiveColor(view);
    }

    @Override
    protected void postAnimation(View view) {
        revertViewBackgroundColor(view);
        goToSelectedActivity(view);
    }

    @Override
    public void handleViewClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    @Override
    protected void showInstructions() {
        showShortToast("Show instructions on how to use Home activity");
    }
}