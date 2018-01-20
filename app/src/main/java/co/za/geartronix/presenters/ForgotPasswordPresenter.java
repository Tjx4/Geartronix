package co.za.geartronix.presenters;

import android.view.View;
import android.widget.EditText;

import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ForgotPasswordActivity;
import co.za.geartronix.views.IForgotPasswordView;

public class ForgotPasswordPresenter extends BaseAppActivityPresenter implements IForgotPasswordPresenter {

    private String oldPassword, newPassword, passwordConfirmation;
    private EditText oldPasswordTxt, newPasswordTxt, passwordConfirmationTxt;

    public ForgotPasswordPresenter(IForgotPasswordView iForgotPasswordView) {
        super((BaseActivity)iForgotPasswordView);
        setDependanciesChildActivities(R.layout.activity_forgot_password);
        currentActionBar.setTitle(" "+activity.getString(R.string.forgot_password));
        setViews();
    }

    public ForgotPasswordPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.ic_launcher);
        setDisplayName(activity.getString(R.string.forgot_password));
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
    public ForgotPasswordActivity getActivity() {
        return null;
    }
}