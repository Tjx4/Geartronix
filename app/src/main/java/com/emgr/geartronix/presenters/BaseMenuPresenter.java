package com.emgr.geartronix.presenters;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;

public abstract class BaseMenuPresenter extends BaseAsyncPresenter {

    protected ViewStub currentPageLayout;
    protected FrameLayout parentLayout;

    protected void setMenuDependencies(BaseActivity activity, String title, int pageLayout) {
        setMainLayout(pageLayout);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        activity.setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout)activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener)activity);
    }

    public boolean handleNavigationItemSelected(MenuItem item) {

        showShortToast("handleNavigationItemSelected");

        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public boolean handleonPrepareOptionsMenu(Menu menu) {
        return true;
    }

    public void handleBackButtonPressed() {
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //getActivity().super.onBackPressed();
        }
    }

    public void setMainLayout(int pageLayout) {
        currentPageLayout = (ViewStub)activity.findViewById(R.id.pageLayout);
        currentPageLayout.setLayoutResource(pageLayout);
    }


    protected View getMainLayout() {
        return currentPageLayout.inflate();
    }


}