package co.za.geartronix.views;

import co.za.geartronix.presenters.SettingsPresenter;

public interface ISettingsView extends IBaseAsyncView {
    SettingsPresenter getPresenter();
}
