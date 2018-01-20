package co.za.geartronix.presenters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.LoginActivity;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.LoginModel;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.providers.MockProvider;
import co.za.geartronix.providers.PermissionsProvider;
import co.za.geartronix.views.ILoginView;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter extends BaseMenuPresenter implements ILoginPresenter {

    private String username, password, welcomeMessage;
    private UserModel user;
    private byte attempts;
    private EditText usernameTxt, passwordTxt;
    private TextView welcomeMessageTxt, usernameLbl, registerBtn;
    private LoginModel responseModel;

    public LoginPresenter(ILoginView iLoginView) {
        super((BaseActivity)iLoginView);
        setDependanciesNoActionBar(R.layout.activity_login);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.content_login);
        setViews();
        responseModel = new LoginModel();
        new PermissionsProvider(getActivity()).requestInternetPermission();
        getLinkedUserOREnterUsername();
    }

    @Override
    public String getPageTitle() {
        return getActivity().getString(R.string.sign_in);
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
    public void signIn(View button) {
        if(attemptsExceeded(button))
            return;

        new DoAsyncCall(button).execute();
    }

    @Override
    public void switchUsers(View view) {
        setEnterUsername();
    }

    @Override
    public void forgotPassword(View view) {
        Bundle extras = new Bundle();
        extras.putBoolean(Constants.USECODE, true);
        goToForgotPassword(extras);
    }

    @Override
    public void setLoginDetails() {

        if(user == null)
            setUsername(usernameTxt.getText().toString());
        else
            setUsername(user.getContactDetailsProvider().getEmails()[0]);

            setPassword(passwordTxt.getText().toString());
    }

    @Override
    public void enterApp() {
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
        parentLayout = getMainLayout();
        loadingScreenFrm = parentLayout.findViewById(R.id.frmLoadingScreen);
        registerBtn = parentLayout.findViewById(R.id.lblRegister);
        usernameTxt = parentLayout.findViewById(R.id.txtUsername);
        passwordTxt = parentLayout.findViewById(R.id.txtPassword);
        welcomeMessageTxt = parentLayout.findViewById(R.id.txtWelcomeMessage);
        usernameLbl = parentLayout.findViewById(R.id.lblUsername);

        passwordTxt.setText("123");
    }

    @Override
    public void getLinkedUserOREnterUsername() {

        Bundle extras = getActivity().getIntent().getExtras();

        if(extras != null)
            extras.getBundle("payload");

        if(extras != null && !extras.isEmpty())  {
            try {
                user = (UserModel) bytes2Object(extras.getByteArray("User"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

user = new MockProvider(getActivity()).getMockUser();

        if(user == null)
            setEnterUsername();
        else
            setLinkedUserDetails();
    }

    @Override
    public void setEnterUsername() {
        usernameTxt.setText("rocboyt@gmail.com");

        welcomeMessageTxt.setVisibility(View.GONE);
        usernameTxt.setVisibility(View.VISIBLE);
        usernameLbl.setVisibility(View.VISIBLE);
        registerBtn.setVisibility(View.GONE);
    }

    @Override
    public void setLinkedUserDetails() {
        welcomeMessage = getActivity().getString(R.string.Hi)+ " "+user.getNames().getFirstName()+ " "+getActivity().getResources().getString(R.string.sign_in_welcome_message);
        welcomeMessageTxt.setText(welcomeMessage);
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
                enterApp();
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
                signIn(button);
                break;
            case R.id.lblRegister:
                switchUsers(button);
                break;
            case R.id.txtForgotPassword:
                forgotPassword(button);
                break;
        }
    }

    public boolean handleNavigationItemSelected(MenuItem item) {
        super.handleNavigationItemSelected(item);

        return true;
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