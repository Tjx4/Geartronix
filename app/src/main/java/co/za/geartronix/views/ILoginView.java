package co.za.geartronix.views;

import android.widget.FrameLayout;

import co.za.geartronix.presenters.LoginPresenter;

public interface ILoginView extends IBaseAsyncView {
    LoginPresenter getPresenter();
}
