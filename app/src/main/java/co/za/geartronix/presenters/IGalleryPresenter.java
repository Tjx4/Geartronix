package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.GalleryActivity;

public interface IGalleryPresenter extends IBaseAsyncPresenter {
    GalleryActivity getActivity();
    void porpulateImageGrid();
    void fullScreeView();
}
