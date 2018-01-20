package co.za.geartronix.presenters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.LoginActivity;
import co.za.geartronix.adapters.UserSelectionAdapter;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.LoginModel;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.providers.PermissionsProvider;
import co.za.geartronix.providers.SQLiteProvider;
import co.za.geartronix.views.ILoginView;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;

public class LoginPresenter extends BaseMenuPresenter implements ILoginPresenter {

    private String username, password, welcomeMessage;
    private UserModel user;
    private byte attempts;
    private EditText usernameTxt, passwordTxt;
    private TextView welcomeMessageTxt, usernameLbl, switchUsersLbl;
    private LoginModel responseModel;
    private int userId;
    private ListView userSelectionLst;
    private FrameLayout userSelectContainerFrm;

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
        //setEnterUsername();
        showUserSelectionView();
    }


    @Override
    public void showUserSelectionView() {
        List<UserModel> users = new SQLiteProvider(getActivity()).getAllUsers();
        UserSelectionAdapter adp = new UserSelectionAdapter(getActivity(), R.layout.user_item,users);
        userSelectionLst.setAdapter(adp);
        userSelectContainerFrm.setVisibility(View.VISIBLE);
    }

    @Override
    public void handleOnUserSelected(View view) {
        RelativeLayout parent = (RelativeLayout)view;
        int userId = parent.getChildAt(0).getId();
        user = new SQLiteProvider(getActivity()).getUser(userId);

        FrameLayout container = (FrameLayout)parent.getParent().getParent().getParent();
        container.setVisibility(View.GONE);

        setLinkedUserDetails();
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
        usernameTxt = parentLayout.findViewById(R.id.txtUsername);
        passwordTxt = parentLayout.findViewById(R.id.txtPassword);
        welcomeMessageTxt = parentLayout.findViewById(R.id.txtWelcomeMessage);
        usernameLbl = parentLayout.findViewById(R.id.lblUsername);
        userSelectContainerFrm = parentLayout.findViewById(R.id.frmUserSelectContainer);
        switchUsersLbl = parentLayout.findViewById(R.id.lblSwitchUser);
        userSelectionLst = parentLayout.findViewById(R.id.lstUserSelection);

        passwordTxt.setText("123");
    }

    @Override
    public void getLinkedUserOREnterUsername() {
        userId = 1;
        user = new SQLiteProvider(getActivity()).getUser(userId);

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
        switchUsersLbl.setVisibility(View.GONE);
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
    protected String getRemoteJson() throws IOException {
        Bundle payload = new Bundle();
        payload.putString("username", username);
        payload.putString("password", password);
        String service = DataServiceProvider.login.getPath();
        String url = environment + service;

        return new HttpConnectionProvider(payload).makeCallForData(url, "GET", true, true, httpConTimeout);
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
        setLoginDetails();

        String response = getRemoteJson();
        responseModel = new LoginModel();
        responseModel.setModel(new JSONObject(response));

        return response;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        if(responseModel.isSuccessful){
            enterApp();
        }
        else {
            showErrorMessage(responseModel.responseMessage, getActivity().getString(R.string.error));
        }

        super.afterAsyncCall(result);
    }

//Todo: Revise
    @Override
    public void handleAsyncButtonClickedEvent(View view) {

        switch (view.getId()) {
            case R.id.btnLogin:
                signIn(view);
                break;
            case R.id.lblSwitchUser:
                switchUsers(view);
                break;
            case R.id.txtForgotPassword:
                forgotPassword(view);
                break;
            case R.id.rltvUserSelect:
                handleOnUserSelected(view);
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