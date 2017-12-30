package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.views.IDiagnosticsView;

public class DiagnosticsPresenter extends co.za.geartronix.presenters.BaseAppActivityPresenter {

    public DiagnosticsPresenter(IDiagnosticsView iDiagnosticsView) {
        super((BaseActivity)iDiagnosticsView);
    }

    public DiagnosticsPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.diagnostics_icon);
        setDisplayName(activity.getString(R.string.diagnostics));
    }

    @Override
    protected void beforeAsyncCall() {

    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }
}
