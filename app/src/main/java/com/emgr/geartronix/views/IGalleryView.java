package com.emgr.geartronix.views;


import com.emgr.geartronix.presenters.GalleryPresenter;

public interface IGalleryView extends IBaseAsyncView{
    GalleryPresenter  getPresenter();
}
