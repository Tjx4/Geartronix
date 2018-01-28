package co.za.geartronix.presenters;

import org.json.JSONException;
import java.io.IOException;
import co.za.geartronix.activities.GalleryActivity;

public interface IGalleryPresenter extends IBaseAsyncPresenter {
    GalleryActivity getActivity();
    void showGallery();
    void fullScreeView();
    String makeGalleryHttpCall() throws IOException;
    String checkServicesUpdate() throws IOException, JSONException;
    String getGallery() throws IOException, JSONException;
}
