package co.za.geartronix.presenters;
import android.view.View;

import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.views.IProfileView;

public class ProfilePresenter extends BaseAppActivityPresenter {

    public ProfilePresenter(IProfileView iProfileView) {
        super((BaseActivity)iProfileView);
    }

    public ProfilePresenter(BaseActivity baseActivity, int index) {
        super(baseActivity);
        activity = baseActivity;
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
