package co.za.geartronix.presenters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.LoginActivity;
import co.za.geartronix.models.LoginModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.ILoginView;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter extends BaseAppActivityPresenter implements ILoginPresenter {

    private String username;
    private byte attempts;
    private String password;
    private EditText usernameTxt;
    private EditText passwordTxt;
    private LoginModel responseModel;

    public LoginPresenter(ILoginView iLoginView) {
        super((BaseActivity)iLoginView);
        setDependancies(R.layout.activity_login);
        setPageTitle(activity.getString(R.string.sign_in));
        setViews();
        responseModel = new LoginModel();
    }

    @Override
    public void configureActionBar() {
        ActionBar ab = basicActionBarConfiguration(" "+activity.getString(R.string.sign_in));
        ab.setLogo(R.mipmap.ic_launcher);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }

    protected boolean attemptsExceeded(View button) {

        if(attempts < 8)
            return false;

        showErrorMessage("You've exceeded the max number os attempts", "Error");
        resetButtonState(button);
        return true;
    }

    @Override
    protected void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i) {
      attempts++;
    }


    @Override
    public void onLoginButtonClicked(View button) {
        if(attemptsExceeded(button))
            return;

        new DoAsyncCall(button).execute();
    }

    @Override
    public void onRegisterClicked(View view) {
        showLongToast("Register");
        resetIfTriggeredByView(view);
    }

    @Override
    public void onForgotPasswordClicked(View view) {
        showLongToast("Forgot password.");
        resetIfTriggeredByView(view);
    }

    @Override
    public void setLoginDetails() {
        setUsername(usernameTxt.getText().toString());
        setPassword(passwordTxt.getText().toString());
    }

    @Override
    public void goToDashBoard() {
        Bundle loginDetails = new Bundle();
        loginDetails.putString("user", responseModel.getUser());
        loginDetails.putInt("userId", responseModel.getUserId());
        loginDetails.putString("session", responseModel.getSession());

        goToHome(loginDetails);
        getActivity().finish();
    }

    @Override
    public LoginActivity getActivity() {
        return (LoginActivity)activity;
    }

    @Override
    public void setViews() {
        setAsyncViews();
        //loginBtn = (Button)findViewById(R.id.btnLogin);
        usernameTxt = (EditText)getActivity().findViewById(R.id.txtUsername);
        passwordTxt = (EditText)getActivity().findViewById(R.id.txtPassword);

        usernameTxt.setText("rocboyt@gmail.com");
        passwordTxt.setText("123");
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    protected void beforeAsyncCall() {
        showLoadingScreen();
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
Log.i(BASE_LOG, "Thread started ... ...");

        setLoginDetails();
        Bundle payload = new Bundle();
        payload.putString("username", username);
        payload.putString("password", password);
        String service = DataServiceProvider.login.getPath();
        String url = environment + service + "index.php";

        return new HttpConnectionProvider(payload).makeCallForData(url, "GET", true, true, httpConTimeout);
    }

    @Override
    protected void afterAsyncCall(Object result) {
        hideLoadingScreen();

        try {
            setResponseModel(result.toString());

            if(responseModel.isSuccessful){
                goToDashBoard();
            }
            else {
                showErrorMessage(responseModel.message, activity.getString(R.string.login_error));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            showErrorMessage(activity.getString(R.string.technical_error), activity.getString(R.string.login_error));
        }

    }

    @Override
    public void setResponseModel(String response) throws JSONException {
        responseModel.setModel(new JSONObject(response));
    }

    //Todo: Revise
    @Override
    public void handleAsyncButtonClickedEvent(View button) {

        switch (button.getId()) {

            case R.id.btnLogin:
                onLoginButtonClicked(button);
                break;
            case R.id.btnRegister:
                onRegisterClicked(button);
                break;
            case R.id.txtForgotPassword:
                onForgotPasswordClicked(button);
                break;

            default:
                showLongToast("Unknown button");
                break;
        }
    }

    @Override
    protected void postAnimation(View view) {
        handleAsyncButtonClickedEvent(view);
    }

    @Override
    public void handleViewClickedEvent(View view) {
        blinkView(view,30,70);
    }

    @Override
    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);
    }
}