package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.RegistrationActivity;
import co.za.geartronix.models.NamesModel;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.providers.ContactDetailsProvider;
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
    protected void beforeAsyncCall() {
        showLoadingScreen();
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {


        return null;
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
    protected void afterAsyncCall(Object result) {
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

        Bundle extras = new Bundle();
        goToLogin(extras);

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
    }

    @Override
    public RegistrationActivity getActivity() {
        return (RegistrationActivity)activity;
    }
}