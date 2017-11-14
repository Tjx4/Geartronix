package com.emgr.geartronix.presenters;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.GalleryActivity;
import com.emgr.geartronix.activities.HomeActivity;
import com.emgr.geartronix.adapters.HomeTileAdapter;
import com.emgr.geartronix.models.AccountModel;
import com.emgr.geartronix.views.IHomeView;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter extends BaseAsyncPresenter implements IHomePresenter {

    private AccountModel responseModel;
    private DrawerLayout mDrawerLayout;
    private GridView homeTileContainer;
    private List<ArrayList> homeItems;

    public HomePresenter(IHomeView iHomeView) {
        setDependanciesNoActionBar((BaseActivity) iHomeView, R.layout.activity_home);
        setViews();
        responseModel = new AccountModel();

        try {

            String user = activity.getIntent().getExtras().getBundle("payload").getString("user");
            showShortToast("Welcome "+user);


            slideOutActivity();
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

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        getActivity().setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener)getActivity());

        // Set tiles
        homeTileContainer = (GridView) getActivity().findViewById(R.id.grdHomeTiles);
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

    @Override
    public boolean handleonPrepareOptionsMenu(Menu menu) {
     return true;
    }

    @Override
    public void handleBackButtonPressed() {
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //getActivity().super.onBackPressed();
        }
    }

    @Override
    public boolean handleNavigationItemSelected(MenuItem item) {

showShortToast("handleNavigationItemSelected");

        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}