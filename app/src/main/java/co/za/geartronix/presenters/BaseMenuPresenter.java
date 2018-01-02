package co.za.geartronix.presenters;

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
import android.widget.RelativeLayout;

import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.HomeActivity;
import co.za.geartronix.constants.Constants;

public abstract class BaseMenuPresenter extends BaseAppActivityPresenter {

    protected ViewStub currentPageLayout;
    protected RelativeLayout parentLayout;

    public BaseMenuPresenter(BaseActivity baseActivity) {
        super(baseActivity);
    }

    public BaseMenuPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
    }

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

        RelativeLayout mainFramelayout = (RelativeLayout) activity.findViewById(R.id.menuContentHomeID);
        //dd.setContentDescription("co.za.geartronix.activities.HomeActivity");

    }

    public boolean handleNavigationItemSelected(MenuItem item) {

        showShortToast("handleNavigationItemSelected");

        // Handle navigation view item clicks here

        switch (item.getItemId()){

            case R.id.action_profile:
                goToProfile();
                break;
            case R.id.action_gallery:
                goToGallery();
                break;
            case R.id.action_make_booking:
                goToServices();
                break;
            case Constants.FINDUSID:
                showShortToast("Find us");
                break;
        }

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
        }
    }

    public void setMainLayout(int pageLayout) {
        currentPageLayout = (ViewStub)activity.findViewById(R.id.pageLayout);
        currentPageLayout.setLayoutResource(pageLayout);
    }


    protected View getMainLayout() {
        return currentPageLayout.inflate();
    }


    public void menuOptionSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_settings:
                showShortToast(activity.getString(R.string.settings));
                break;
        }
    }

}