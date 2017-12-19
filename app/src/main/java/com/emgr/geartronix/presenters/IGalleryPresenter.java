package com.emgr.geartronix.presenters;

import android.view.View;
import com.emgr.geartronix.activities.GalleryActivity;

public interface IGalleryPresenter extends IBaseAsyncPresenter {
    void setViews();
    GalleryActivity getActivity();
    void openDetailedView(View view);
    void closeDetailedView(View view);
    void porpulateGrid();
}
