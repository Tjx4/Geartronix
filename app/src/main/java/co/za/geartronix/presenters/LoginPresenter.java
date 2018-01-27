package co.za.geartronix.presenters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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
import co.za.geartronix.views.ILoginView;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;

public class LoginPresenter extends BaseSlideMenuPresenter implements ILoginPresenter {

    private String username, password, welcomeMessage;
    private UserModel user;
    private byte attempts;
    private EditText usernameTxt, passwordTxt;
    private TextView welcomeMessageTxt, usernameLbl, switchUsersLbl;
    private LoginModel loginModel;
    private int userId;
    private ListView userSelectionLst;
    private FrameLayout userSelectContainerFrm;
    private boolean isUserDialogOpened;

    public LoginPresenter(ILoginView iLoginView) {
        super((BaseActivity)iLoginView);
        setDependanciesNoActionBar(R.layout.activity_login);
        setSlideMenuDependencies(getActivity(), getPageTitle(), R.layout.content_login);
        loginModel = new LoginModel();
        setViews();
        getLinkedUserOREnterUsername();

        permissionProvider.requestInternetPermission();
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
    public void switchUsers() {
        isUserDialogOpened = true;
        showUserSelectionView();
    }


    @Override
    public void showUserSelectionView() {
        List<UserModel> users = sqLiteProvider.getAllUsers();
        UserSelectionAdapter adp = new UserSelectionAdapter(getActivity(), R.layout.user_item,users);
        userSelectionLst.setAdapter(adp);
        userSelectContainerFrm.setVisibility(View.VISIBLE);
        fadeInOverlay(userSelectContainerFrm);
    }

    @Override
    public void hideUserSelectionView() {
        fadeOutOverlay(userSelectContainerFrm);
    }

    @Override
    public void handleOnUserSelected(View view) {
        RelativeLayout parent = (RelativeLayout)view;
        int userId = parent.getChildAt(0).getId();
        user = sqLiteProvider.getUser(userId);

        FrameLayout container = (FrameLayout)parent.getParent().getParent().getParent();
        hideUserSelectionView();

        setLinkedUserDetails();
        isUserDialogOpened = false;

        passwordTxt.setText("");
    }

    public boolean allowKeyDown(int keyCode, KeyEvent event) {
        if(isUserDialogOpened) {
            hideUserSelectionView();
            isUserDialogOpened = false;
            return false;
        }
        else {
            return true;
        }
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
        loginDetails.putString("userName", loginModel.getUserName());
        loginDetails.putInt("userId", loginModel.getUserId());
        loginDetails.putString("session", loginModel.getSession());

        goToDashboard(loginDetails);
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
        laodingTxt = parentLayout.findViewById(R.id.txtLoading);
        loadingProgressBar = parentLayout.findViewById(R.id.progressBarLoading);
        loadingImg = parentLayout.findViewById(R.id.imgLoading);
        loadingScreenFrm = parentLayout.findViewById(R.id.frmLoadingScreen);
        usernameTxt = parentLayout.findViewById(R.id.txtUsername);
        passwordTxt = parentLayout.findViewById(R.id.txtPassword);
        welcomeMessageTxt = parentLayout.findViewById(R.id.txtWelcomeMessage);
        usernameLbl = parentLayout.findViewById(R.id.lblUsername);
        userSelectContainerFrm = parentLayout.findViewById(R.id.frmUserSelectContainer);
        switchUsersLbl = parentLayout.findViewById(R.id.lblSwitchUser);
        userSelectionLst = parentLayout.findViewById(R.id.lstUserSelection);

        fadeOutOverlay(userSelectContainerFrm);
    }

    @Override
    public void getLinkedUserOREnterUsername() {
        userId = 1;
        Bundle extras = getActivity().getIntent().getExtras();

        if(extras != null) {
            Bundle bundle_extras = extras.getBundle(Constants.PAYLOAD);

            if(bundle_extras != null)
                userId = bundle_extras.getInt(Constants.USERID);
        }

        user = sqLiteProvider.getUser(userId);

        if(user == null)
            setEnterUsername();
        else
            setLinkedUserDetails();
    }

    @Override
    public void setEnterUsername() {
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
    protected String getRemoteJson(int methodIndex) throws IOException {
        if (methodIndex == 0)
            return signIn();
        else
            return null;
    }

    @Override
    public String signIn() throws IOException {
        String service = DataServiceProvider.login.getPath();
        String url = environment + service;

        Bundle payload = new Bundle();
        payload.putString("username", username);
        payload.putString("password", password);

        return new HttpConnectionProvider(payload).makeCallForData(url, "GET", true, true, httpConTimeout);
    }

    @Override
    protected void beforeAsyncCall() {
        super.beforeAsyncCall();
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(int actionIndex) throws Exception {
        this.actionIndex = actionIndex;
        setLoginDetails();
        loginModel = new LoginModel();
        String response = getRemoteJson(actionIndex);
        loginModel.setModel(new JSONObject(response));
        return response;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        if(loginModel.isSuccessful){
            showPostAsyncSuccess();
            enterApp();
            getActivity().finish();
        }
        else {
            super.afterAsyncCall(result);
            passwordTxt.setText("");
            showErrorMessage(loginModel.responseMessage, getActivity().getString(R.string.error));
        }

    }

    public boolean handleNavigationItemSelected(MenuItem item) {
        super.handleNavigationItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_register:
                goToRegistration();
                break;
            case R.id.action_switch_users:
                switchUsers();
                break;
        }
        return true;
    }



    @Override
    public void checkAndSignIn() {
        if(passwordTxt.getText().toString().isEmpty()) {
            showShortToast(getActivity().getString(R.string.enter_password));
        }
        else {
            new DoAsyncCall().execute(0);
        }
    }

    @Override
    protected void postAnimation(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                checkAndSignIn();
                break;
            case R.id.lblSwitchUser:
                switchUsers();
                break;
            case R.id.txtForgotPassword:
                forgotPassword(view);
                break;
            case R.id.rltvUserSelect:
                handleOnUserSelected(view);
                break;
            case R.id.imgBtnPasswordViewtoggle:
                togglePasswordFieldView(passwordTxt, (ImageButton) view);
                break;
        }
    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {
        blinkView(view,30,70);
    }

    @Override
    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);
    }
}