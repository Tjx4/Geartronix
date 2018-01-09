package co.za.geartronix.presenters;

import android.view.MenuItem;
import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.GalleryActivity;
import co.za.geartronix.models.DiagnosticsModel;
import co.za.geartronix.views.IDiagnosticsView;

public class DiagnosticsPresenter extends BaseAppActivityPresenter implements IGalleryPresenter {

     private DiagnosticsModel responseModel;

    public DiagnosticsPresenter(IDiagnosticsView iDiagnosticsView) {
        super((BaseActivity)iDiagnosticsView);
        setDependanciesChildActivities(R.layout.activity_diagnostics);
        currentActionBar.setTitle(" "+activity.getString(R.string.diagnostics));
        setViews();
        responseModel = new DiagnosticsModel();
        new DoAsyncCall().execute();
    }

    public DiagnosticsPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.diagnostics_icon);
        setDisplayName(activity.getString(R.string.diagnostics));
    }

    @Override
    protected void postAnimation(View view) {
        showShortToast("View clicked");
    }

    @Override
    public void handleViewClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    @Override
    public void setViews() {

    }

    @Override
    public GalleryActivity getActivity() {
        return null;
    }

    @Override
    public void porpulateServiceList() {

    }

    @Override
    public void fullScreeView() {

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

    public void menuOptionSelected(MenuItem item) {
        showShortToast("menuOptionSelected");
    }

    @Override
    public void goToCurrentAppActivity() {
        goToDiagnostics();
    }
}
