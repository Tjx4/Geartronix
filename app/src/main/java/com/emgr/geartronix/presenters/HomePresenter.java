package com.emgr.geartronix.presenters;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.GalleryActivity;
import com.emgr.geartronix.activities.HomeActivity;
import com.emgr.geartronix.models.AccountModel;
import com.emgr.geartronix.views.IHomeView;

public class HomePresenter extends BaseAsyncPresenter implements IHomePresenter {

    private AccountModel responseModel;
    private DrawerLayout mDrawerLayout;

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
    public void saveLoginDetails() {
        //String user = activity.getIntent().getExtras().getBundle("payload").getString("user");
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
        getActivity().setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener)getActivity());
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
