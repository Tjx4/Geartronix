package com.emgr.geartronix.presenters;

import android.graphics.Color;

public abstract class BaseAppActivityPresenter extends BaseAsyncPresenter {

    protected int icon;
    protected Color activeColor, inactivecolor;
    protected String activityName, displayName;
    protected BaseAppActivityPresenter currentAppActivity;

    public BaseAppActivityPresenter() {
       activityName = this.getClass().toString();
       displayName = this.getClass().getSimpleName();
       currentAppActivity = this;
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

    public Color getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(Color activeColor) {
        this.activeColor = activeColor;
    }

    public Color getInactivecolor() {
        return inactivecolor;
    }

    public void setInactivecolor(Color inactivecolor) {
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
