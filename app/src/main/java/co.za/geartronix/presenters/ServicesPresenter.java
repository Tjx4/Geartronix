package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ServicesActivity;
import co.za.geartronix.views.IServicesView;

public class ServicesPresenter extends BaseMenuPresenter implements IServicesPresenter {


    public ServicesPresenter(IServicesView iServicesView) {
        super((BaseActivity)iServicesView);
        setDependanciesNoActionBar(R.layout.activity_home);
        pageTitle = getActivity().getString(R.string.services);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.activity_service );
        setViews();
    }

    public ServicesPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.service_icon);
        setDisplayName(activity.getString(R.string.services));
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
    public void setViews() {

    }

    @Override
    public ServicesActivity getActivity() {
        return (ServicesActivity)activity;
    }

    @Override
    public void handleViewClickedEvent(View view) {

    }

    @Override
    public void goToCurrentAppActivity() {
        goToServices();
    }

}
