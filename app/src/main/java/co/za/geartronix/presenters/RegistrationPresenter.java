package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.RegistrationActivity;
import co.za.geartronix.models.MemberModel;
import co.za.geartronix.models.NamesModel;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.providers.ContactDetailsProvider;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.IRegistrationView;

public class RegistrationPresenter extends BaseMenuPresenter implements IRegistrationPresenter {

    private UserModel user;
    private String title, firstName, city, email, cellNumber, password, passwordConfirmation;
    private EditText nametxt, cityTxt, cellTxt, emailTxt, passwordTxt, passwordConfirmationTxt;

    public RegistrationPresenter(IRegistrationView iRegistrationView) {
        super((BaseActivity)iRegistrationView);
        setDependanciesNoActionBar(R.layout.activity_registration);
        title = getActivity().getString(R.string.create_account);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.content_registration);
        setViews();
    }

    @Override
    public String getPageTitle() {
        return title;
    }

    @Override
    protected String getRemoteJson() throws IOException {

        String service = DataServiceProvider.registration.getPath();
        String url = environment + service;

        Bundle payload = new Bundle();
        payload.putString("name", firstName);
        payload.putString("password", password);
        payload.putString("city", city);
        payload.putString("email", email);
        payload.putString("cellNumber", cellNumber);

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
    protected Object doAsyncOperation(Object... args) throws Exception {
        user = new UserModel();
        NamesModel names = new NamesModel();
        names.setFirstName(firstName);
        user.setNames(names);
        user.setCity(city);
        ContactDetailsProvider contactDetails = new ContactDetailsProvider();
        int cell1 = Integer.parseInt(cellNumber);
        contactDetails.setContactNumbers(new int[]{cell1});
        contactDetails.setEmails(new String[]{email});
        user.setContactDetailsProvider(contactDetails);
        MemberModel memberModel = new MemberModel();
        memberModel.setMemberType(0);
        user.setMemberType(memberModel);
        try {
            addUserToDataBase(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        String response = getRemoteJson();
        user = new UserModel();
        user.setModel(new JSONObject(response));
        return response;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        if(user.isSuccessful) {
            goToLogin();
        }
        else {
            showErrorMessage(user.getResponseMessage(), getActivity().getString(R.string.error));
        }

        super.afterAsyncCall(result);
    }

    @Override
    public void setRegProperties() {
        firstName = nametxt.getText().toString();
        password = passwordTxt.getText().toString();
        passwordConfirmation = passwordConfirmationTxt.getText().toString();
        city = cityTxt.getText().toString();
        email = emailTxt.getText().toString();
        cellNumber = cellTxt.getText().toString();
    }

    @Override
    public void registerUser() {

    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    protected void postAnimation(View view) {
        setRegProperties();

        if(!isValidPasswordCreation(password, passwordConfirmation) || !isValidName(firstName) || !isValidCell(cellNumber)) {
            hideLoadingScreen();

            if(!isValidName(firstName))
                showErrorMessage("Please enter a valid username", "Username error");
            else if(!isValidCell(cellNumber))
                showErrorMessage("Please enter a valid cellphone number", "Cell number error");
            else if(!isValidPassword(password))
                showErrorMessage("Your password does not meet minimum requirements", "Password error");
            else if(!isMatchPasswords(password, passwordConfirmation))
                showErrorMessage("Your passwords don't match", "Password error");
            else
                showErrorMessage("Please enter all details correctly", "Error");
        }
        else {
            new DoAsyncCall().execute();
        }
    }

    @Override
    public void handleViewClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    @Override
    public void setViews() {
        parentLayout = getMainLayout();
        loadingScreenFrm = parentLayout.findViewById(R.id.frmLoadingScreen);
        nametxt = parentLayout.findViewById(R.id.txtName);
        passwordTxt = parentLayout.findViewById(R.id.txtPassword);
        passwordConfirmationTxt = parentLayout.findViewById(R.id.txtConfirmPassword);
        cityTxt = parentLayout.findViewById(R.id.txtCity);
        cellTxt = parentLayout.findViewById(R.id.txtCell);
        emailTxt = parentLayout.findViewById(R.id.txtEmail);

        nametxt.setText("Rob");
        cellTxt.setText("0842630120");
        emailTxt.setText("rob@gmail.com");
        cityTxt.setText("Pretoria");
        passwordTxt.setText("Tl@0793079399");
        passwordConfirmationTxt.setText("Tl@0793079399");
    }

    @Override
    public RegistrationActivity getActivity() {
        return (RegistrationActivity)activity;
    }
}