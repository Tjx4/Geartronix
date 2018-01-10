package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.views.ISettingsView;

public class SettingsPresenter extends BaseAppActivityPresenter implements ISettingsPresenter {


    public SettingsPresenter(ISettingsView iSettingsView) {
        super((BaseActivity)iSettingsView);
        setDependanciesChildActivities(R.layout.activity_settings_activty);
        currentActionBar.setTitle(" "+activity.getString(R.string.settings));
        setViews();
        //responseModel = new GalleryModel();
    }

    public SettingsPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.drawable.settings_icon);
        setDisplayName(activity.getString(R.string.settings));
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

    @Override
    public void handleViewClickedEvent(View view) {

    }

    @Override
    public void setViews() {

    }
}