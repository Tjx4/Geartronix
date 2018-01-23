package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ServicesActivity;
import co.za.geartronix.views.IServicesView;

public class ServicesPresenter extends BaseSlideMenuPresenter implements IServicesPresenter {


    public ServicesPresenter(IServicesView iServicesView) {
        super((BaseActivity)iServicesView);
        setDependanciesNoActionBar(R.layout.activity_dashboard);
        pageTitle = getActivity().getString(R.string.services);
        setSlideMenuDependencies(getActivity(), getPageTitle(), R.layout.activity_service );
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
    protected Object doAsyncOperation(int actionIndex) throws Exception {
        this.actionIndex = actionIndex;

        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

    }

    @Override
    public void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    public void setViews() {
    }

    @Override
    public ServicesActivity getActivity() {
        return (ServicesActivity)activity;
    }

    @Override
    public void goToCurrentAppActivity() {
        goToServices();
    }

}