package com.emgr.geartronix.views;

import android.view.View;

import com.emgr.geartronix.presenters.HomePresenter;

public interface IHomeView extends IBaseAsyncView{
    HomePresenter getPresenter();
    void onTileClicked(View view);
}
