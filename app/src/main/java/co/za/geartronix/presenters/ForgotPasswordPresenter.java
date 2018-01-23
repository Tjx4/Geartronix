package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ForgotPasswordActivity;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.views.IForgotPasswordView;

public class ForgotPasswordPresenter extends BaseAppActivityPresenter implements IForgotPasswordPresenter {

    private boolean useCode;
    private String oldPassword, newPassword, passwordConfirmation;
    private EditText codeTxt, oldPasswordTxt, newPasswordTxt, passwordConfirmationTxt;
    private TextView codeOrOldPasswordLbl;

    public ForgotPasswordPresenter(IForgotPasswordView iForgotPasswordView) {
        super((BaseActivity)iForgotPasswordView);
        setDependanciesChildActivities(R.layout.activity_forgot_password);
        currentActionBar.setTitle(" "+activity.getString(R.string.change_password));

        try {
            Bundle extras = getActivity().getIntent().getExtras();
            useCode = extras.getBundle("payload").getBoolean(Constants.USECODE);
        }
        catch (Exception e){

        }

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
    public void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    public void setViews() {
        oldPasswordTxt = (EditText) getActivity().findViewById(R.id.txtOldPassword);
        codeOrOldPasswordLbl = (TextView) getActivity().findViewById(R.id.lblCodeOrOldPassword);
        codeTxt = (EditText) getActivity().findViewById(R.id.txtCode);
        newPasswordTxt = (EditText) getActivity().findViewById(R.id.txtPassword);
        passwordConfirmationTxt = (EditText) getActivity().findViewById(R.id.txtConfirmPassword);

        if(useCode) {
            oldPasswordTxt.setVisibility(View.GONE);
            codeTxt.setVisibility(View.VISIBLE);
            codeOrOldPasswordLbl.setText("*Enter password renewal code");
        }
        else {
            oldPasswordTxt.setVisibility(View.VISIBLE);
            codeTxt.setVisibility(View.GONE);
            codeOrOldPasswordLbl.setText("*Enter your old password");
        }
    }

    @Override
    public ForgotPasswordActivity getActivity() {
        return (ForgotPasswordActivity)activity;
    }
}