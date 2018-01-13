package co.za.geartronix.presenters;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import co.za.geartronix.activities.ProfileActivity;

interface IProfilePresenter  extends IBaseAsyncPresenter {
    ProfileActivity getActivity();
    boolean isProfileChanged();
    void addCar(View view);
    void updateProfile();
    void bookAService(View view);
    void askQuestion(View view);
    void uploadPicture(View view);
    void viewMessages(View view);
    void viewPoints(View view);
    void showMoreOptions(View view);
    void viewCars(View view);
    void setViewMode();
    void setEditMode();
    void toggleModes();
    void setMessageCount(int count);
    void setCarsCount(int count);
    void confirmChanges();
    void setPointsCount(int count);
    MenuItem getModeMenuItem();
    void setUsername(String username);
    void setMemberType(String userType);
    void setCity(String city);
    void setProfPic(Bitmap image);
    ActionBar profileEditActionBar();
    void setProfileDetails();
}