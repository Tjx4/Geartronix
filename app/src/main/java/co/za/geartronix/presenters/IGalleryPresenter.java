package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.GalleryActivity;

public interface IGalleryPresenter extends IBaseAsyncPresenter {
    GalleryActivity getActivity();
    void shareImage(View view);
    void porpulateImageGrid();
    void showEnlargedImage(View view);
    void hideEnlargedImage();
    String getImageName();
    void saveCurrentImageToGallery();
    void showPanels();
    void hidePanels();
    void fullScreeView();;
}
