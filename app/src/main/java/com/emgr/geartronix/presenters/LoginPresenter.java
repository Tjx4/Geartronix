package com.emgr.geartronix.presenters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.providers.DataServiceProvider;
import com.emgr.geartronix.providers.HttpConnectionProvider;
import com.emgr.geartronix.views.ILoginViewI;

public class LoginPresenter extends BaseAsyncPresenter implements ILoginPresenter {

    private String username;
    private byte attempts;
    private String password;
    private EditText usernameTxt;
    private EditText passwordTxt;

    public LoginPresenter(ILoginViewI iLoginView) {
        activity = (BaseActivity) iLoginView;
    }

    @Override
    public void onCreate() {
        slideInActivity();
        comonOnCreate(R.layout.activity_login);
        configureActionBar();
        setViews();
    }

    @Override
    public void configureActionBar() {
        ActionBar ab = basicActionBarConfiguration(activity.getString(R.string.app_name));
        ab.setLogo(R.mipmap.ic_launcher);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        /*ab.setTitle(getResources().getString(R.string.app_name));
        ab.setDisplayHomeAsUpEnabled(true);*/
    }

    protected boolean attemptsExceeded(View button) {

        if(attempts < 4)
            return false;

        showErrorMessage("You've exceeded the max number os attempts", "Error");
        resetButtonState(button);
        return true;
    }

    @Override
    protected void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i)
    {
      showLongToast("Positive button works");
    }
    @Override
    protected  void onNagativeButtonClicked(DialogInterface dialogInterface, int i)
    {
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
    public void onRegisterClicked() {

    }

    @Override
    public void onForgotPasswordClicked() {

    }

    private void setViews() {
        //loginBtn = (Button)findViewById(R.id.btnLogin);
        usernameTxt = (EditText)activity.findViewById(R.id.txtUsername);
        passwordTxt = (EditText)activity.findViewById(R.id.txtPassword);
    }


    @Override
    public void setLoginDetails() {
        setUsername(usernameTxt.getText().toString());
        setPassword(passwordTxt.getText().toString());
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
        String url = environment + DataServiceProvider.login + "index.php";

        return new HttpConnectionProvider(payload).makeCallForData(url, "GET", true, true, httpConTimeout);

    }

    @Override
    protected void afterAsyncCall(Object result) {

    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

        switch (button.getId()) {

            case R.id.btnLogin:
                onLoginButtonClicked(button);
                break;

            default:
                showLongToast("Unknown button");
                break;
        }
    }
}