package com.emgr.geartronix.presenters;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.GridView;
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
    private GridView homeTileContainer;
    private List<ArrayList> homeItems;

    public HomePresenter(IHomeView iHomeView) {
        setDependanciesNoActionBar((BaseActivity) iHomeView, R.layout.activity_home);
        setMenuDependencies(getActivity(), getActivity().getString(R.string.app_name), R.layout.content_home );
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

            case R.id.action_gallery:
                goToActivity(GalleryActivity.class);
                break;
        }
    }

    @Override
    public void setViews() {

        // Set tiles
        FrameLayout parentLayout =  (FrameLayout)currentPageLayout.inflate();

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

        setActiveInactiveColor(view);

        switch (view.getId()){

            case Constants.PROFILEID:
                showShortToast("Profile");
            break;
            case Constants.BOOKSERVICEID:
                showShortToast("book service");
            break;
            case Constants.GALLERYID:
                showShortToast("Gallery");
            break;
            case Constants.FINDUSID:
                showShortToast("Find us");
            break;
        }

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