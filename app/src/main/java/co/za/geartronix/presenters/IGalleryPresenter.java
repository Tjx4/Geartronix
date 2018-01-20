package co.za.geartronix.presenters;

import co.za.geartronix.activities.GalleryActivity;

public interface IGalleryPresenter extends IBaseAsyncPresenter {
    GalleryActivity getActivity();
    void porpulateServiceList();
    void fullScreeView();
}
