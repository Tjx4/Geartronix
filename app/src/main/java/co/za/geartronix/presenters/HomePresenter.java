package co.za.geartronix.presenters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
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
            showShortToast(activity.getString(R.string.welcome)+" "+user);

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
        parentLayout = getMainLayout();
        homeHeaderText = parentLayout.findViewById(R.id.txtHomeHeader);
        selectedActivityImg = parentLayout.findViewById(R.id.imgSelectedActivity);

        // Set tiles
        homeTileContainer = parentLayout.findViewById(R.id.grdHomeTiles);
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
        // int viewId = view.getId();
        blinkView(view, 30, 70);
    }

}