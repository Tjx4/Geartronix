package co.za.geartronix.presenters;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.view.View;
import co.za.geartronix.activities.ProfileActivity;

interface IProfilePresenter  extends IBaseAsyncPresenter {
    ProfileActivity getActivity();
    void addCar(View view);
    void bookAService(View view);
    void askQuestion(View view);
    void uploadPicture(View view);
    void viewMessages(View view);
    void viewPoints(View view);
    void showMoreOptions(View view);
    void viewCars(View view);
    void setEditMode();
    void setMessageCount(int count);
    void setCarsCount(int count);
    void setPointsCount(int count);
    void setViewMode();
    void setUsername(String username);
    void setMemberType(String userType);
    void setCity(String city);
    void setProfPic(Bitmap image);
    ActionBar profileEditActionBar();
    void setProfileDetails();
}