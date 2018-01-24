package co.za.geartronix.presenters;

import java.io.IOException;
import co.za.geartronix.activities.GalleryActivity;

public interface IGalleryPresenter extends IBaseAsyncPresenter {
    GalleryActivity getActivity();
    void porpulateServiceList();
    void fullScreeView();
    String getGallery() throws IOException;
}
