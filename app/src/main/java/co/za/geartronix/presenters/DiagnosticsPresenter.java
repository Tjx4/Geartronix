package co.za.geartronix.presenters;

import android.view.MenuItem;
import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.DiagnosticsActivity;
import co.za.geartronix.models.DiagnosticsModel;
import co.za.geartronix.views.IDiagnosticsView;

public class DiagnosticsPresenter extends BaseAppActivityPresenter implements IDiagnosticsPresenter {

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
    public void setViews() {
    }

    @Override
    public DiagnosticsActivity getActivity() {
        return (DiagnosticsActivity)activity;
    }

    @Override
    protected void beforeAsyncCall() {

    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(int actionIndex) throws Exception {
        this.actionIndex = actionIndex;

        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);

        //showShortToast("menuOptionSelected");
    }

    @Override
    public void goToCurrentAppActivity() {
        goToDiagnostics();
    }
}