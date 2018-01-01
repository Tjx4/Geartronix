package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.ProfileActivity;

interface IProfilePresenter  extends IBaseAsyncPresenter {
    ProfileActivity getActivity();
    void addCar(View view);
    void uploadPicture(View view);
    void viewMessages(View view);
    void viewPoints(View view);
    void setEditMode();
    void setViewMode();
}