package co.za.geartronix.presenters;

import android.view.View;

import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.views.IFindUsView;

public class FindUsPresenter extends BaseAppActivityPresenter {

    public FindUsPresenter(IFindUsView iFindUsView) {
        super((BaseActivity)iFindUsView);
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
}