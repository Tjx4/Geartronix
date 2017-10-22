package com.emgr.geartronix.presenters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.LoginActivity;
import com.emgr.geartronix.models.LoginModel;
import com.emgr.geartronix.providers.DataServiceProvider;
import com.emgr.geartronix.providers.HttpConnectionProvider;
import com.emgr.geartronix.views.ILoginView;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter extends BaseAsyncPresenter implements ILoginPresenter {

    private String username;
    private byte attempts;
    private String password;
    private EditText usernameTxt;
    private EditText passwordTxt;
    private LoginModel responseModel;

    public LoginPresenter(ILoginView iLoginView) {
        activity = (BaseActivity) iLoginView;
        slideInActivity();
        comonOnCreate(R.layout.activity_login);
        configureActionBar();
        setViews();
        responseModel = new LoginModel();
    }

    @Override
    public void configureActionBar() {
        ActionBar ab = basicActionBarConfiguration(" "+activity.getString(R.string.sign_in));
        ab.setLogo(R.drawable.signin_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        /*ab.setTitle(getResources().getString(R.string.app_name));
        ab.setDisplayHomeAsUpEnabled(true);*/
    }

    protected boolean attemptsExceeded(View button) {

        if(attempts < 8)
            return false;

        showErrorMessage("You've exceeded the max number os attempts", "Error");
        resetButtonState(button);
        return true;
    }

    @Override
    protected void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i){
      showLongToast("Positive button works");
    }

    @Override
    protected  void onNagativeButtonClicked(DialogInterface dialogInterface, int i){
      showLongToast("Nagative button works");
    }

    @Override
    public void onLoginButtonClicked(View button) {
        if(attemptsExceeded(button))
            return;

        new DoAsyncCall(button).execute();
        attempts++;
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

    private void setViews() {
        //loginBtn = (Button)findViewById(R.id.btnLogin);
        usernameTxt = (EditText)getActivity().findViewById(R.id.txtUsername);
        passwordTxt = (EditText)getActivity().findViewById(R.id.txtPassword);
        loadingScreenFrm = (FrameLayout) getActivity().findViewById(R.id.frmLoadingScreen);
    }


    @Override
    public void setLoginDetails() {
        setUsername(usernameTxt.getText().toString());
        setPassword(passwordTxt.getText().toString());
    }

    @Override
    public void goToDashBoard() {
        //goToActivity();
    }

    @Override
    public LoginActivity getActivity() {
        return (LoginActivity)activity;
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

        try {
            setResponseModel(result.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        showShortToast("session:" + responseModel.session + "| message:" + responseModel.message + "| isSuccessful:" + responseModel.isSuccessful);

        hideLoadingScreen();
    }

    @Override
    public void setResponseModel(String response) throws JSONException {
        JSONObject responseJson = new JSONObject(response);
        responseModel.setResponse(responseJson.toString());
        responseModel.setMessage(responseJson.getString("message"));
        responseModel.setSuccessful(responseJson.getBoolean("isSuccessful"));
        responseModel.setSession(responseJson.getString("session"));
    }


    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

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
}