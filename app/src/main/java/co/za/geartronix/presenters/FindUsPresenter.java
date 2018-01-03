package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.FindUsActivity;
import co.za.geartronix.views.IFindUsView;

public class FindUsPresenter extends BaseAppActivityPresenter implements IFindUsPresenter {

    public FindUsPresenter(IFindUsView iFindUsView) {
        super((BaseActivity)iFindUsView);

        // Go to Google maps
    }

    public FindUsPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.find_us_icon);
        setDisplayName(activity.getString(R.string.FindUs));
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

    @Override
    public FindUsActivity getActivity() {
        return (FindUsActivity)activity;
    }
}