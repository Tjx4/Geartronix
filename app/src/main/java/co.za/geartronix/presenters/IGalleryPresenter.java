package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.GalleryActivity;

public interface IGalleryPresenter extends IBaseAsyncPresenter {
    void setViews();
    GalleryActivity getActivity();
    void openDetailedView(View view);
    void closeDetailedView(View view);
    void porpulateGrid();
    void showEnlargedImage(View view);
    void hideEnlargedImage();
}
