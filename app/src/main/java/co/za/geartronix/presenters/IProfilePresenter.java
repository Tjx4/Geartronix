package co.za.geartronix.presenters;

import android.view.View;

interface IProfilePresenter  extends IBaseAsyncPresenter {
    void addCar();
    void uploadPicture(View view);
    void viewMessages(View view);
    void viewPoints(View view);
}
