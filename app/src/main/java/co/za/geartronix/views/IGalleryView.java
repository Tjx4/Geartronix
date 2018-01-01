package co.za.geartronix.views;


import co.za.geartronix.presenters.GalleryPresenter;

public interface IGalleryView extends IBaseAsyncView{
    GalleryPresenter  getPresenter();
}
