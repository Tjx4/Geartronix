package co.za.geartronix.views;

import co.za.geartronix.presenters.MessagesPresenter;

public interface IMessagesView extends IBaseAsyncView {
    MessagesPresenter getPresenter();
}