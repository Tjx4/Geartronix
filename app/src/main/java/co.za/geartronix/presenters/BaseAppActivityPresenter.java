package co.za.geartronix.presenters;

import android.graphics.Color;

import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;

public abstract class BaseAppActivityPresenter extends BaseAsyncPresenter {

    protected int AppActivityIndex;
    protected int icon;
    protected int activeColor;
    protected int inactivecolor;
    protected String activityName, displayName;
    protected BaseAppActivityPresenter currentAppActivity;

    public BaseAppActivityPresenter(BaseActivity baseActivity, int index) {
        setProperties(baseActivity, index);
    }

    public BaseAppActivityPresenter(BaseActivity baseActivity) {
        activity = baseActivity;
    }

    public void setProperties(BaseActivity baseActivity, int index) {
       activity = baseActivity;
       setActivityName( this.getClass().toString() );
       setCurrentAppActivity(this);
       setActiveColor(Color.parseColor("#0088cc"));
       setInactivecolor(activity.getResources().getColor(R.color.tileTransBlack));
    }

    public String getFormatedName(String name) {
       String formatedName = name.split("Presenter")[0];
       return formatedName;
    }

    public void goToCurrentAppActivity() {
        showShortToast(displayName);
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
    }

    public int getInactivecolor() {
        return inactivecolor;
    }

    public void setInactivecolor(int inactivecolor) {
        this.inactivecolor = inactivecolor;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BaseAppActivityPresenter getCurrentAppActivity() {
        return currentAppActivity;
    }

    public void setCurrentAppActivity(BaseAppActivityPresenter currentAppActivity) {
        this.currentAppActivity = currentAppActivity;
    }

}