package com.emgr.geartronix.presenters;

import android.view.MenuItem;

public interface IDashboardPresenter extends IBaseAsyncPresenter {
    void saveLoginDetails();
    void menuOptionSelected(MenuItem item);
}
