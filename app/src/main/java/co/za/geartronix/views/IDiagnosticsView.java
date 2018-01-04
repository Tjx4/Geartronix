package co.za.geartronix.views;

import co.za.geartronix.presenters.DiagnosticsPresenter;

public interface IDiagnosticsView extends IBaseAsyncView {
    DiagnosticsPresenter getPresenter();
}
