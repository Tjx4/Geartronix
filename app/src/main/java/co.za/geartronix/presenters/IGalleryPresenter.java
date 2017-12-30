package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.GalleryActivity;

public interface IGalleryPresenter extends IBaseAsyncPresenter {
    void setViews();
    GalleryActivity getActivity();
    void shareImage(View view);
    void porpulateImageGrid();
    void showEnlargedImage(View view);
    void hideEnlargedImage();
    String getImageName();
}
