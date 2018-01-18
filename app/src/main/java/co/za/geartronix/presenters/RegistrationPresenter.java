package co.za.geartronix.presenters;


import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.RegistrationActivity;
import co.za.geartronix.views.IRegistrationView;

public class RegistrationPresenter extends BaseMenuPresenter implements IRegistrationPresenter {

    public RegistrationPresenter(IRegistrationView iRegistrationView) {
        super((BaseActivity)iRegistrationView);
        setDependanciesNoActionBar(R.layout.activity_registration);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.content_registration);
        setViews();
    }

    @Override
    public String getPageTitle() {
        return getActivity().getString(R.string.register);
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
    public RegistrationActivity getActivity() {
        return (RegistrationActivity)activity;
    }
}
