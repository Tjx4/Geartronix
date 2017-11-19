package com.emgr.geartronix.presenters;

import android.view.View;
import com.emgr.geartronix.activities.GalleryActivity;

public interface IGalleryPresenter extends IBaseAsyncPresenter {
    void setViews();
    GalleryActivity getActivity();
    void handleButtonClickedEvent(View view);
}
