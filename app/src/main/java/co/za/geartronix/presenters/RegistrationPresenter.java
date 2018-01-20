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

    private String title, firstName, city, email, cellNumber;
    private UserModel user;
    private EditText nametxt, cityTxt, cellTxt, emailTxt;

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

        if(!isValidName(firstName) || !isValidCell(cellNumber)){
            hideLoadingScreen();

            if(!isValidName(firstName))
                showErrorMessage("Please enter a valid username", "Error");
            else if(!isValidName(cellNumber))
                showErrorMessage("Please enter a valid cellphone number", "Error");
            else
                showErrorMessage("Please enter all details", "Error");
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
        cityTxt = parentLayout.findViewById(R.id.txtCity);
        cellTxt = parentLayout.findViewById(R.id.txtCell);
        emailTxt = parentLayout.findViewById(R.id.txtEmail);
    }

    @Override
    public RegistrationActivity getActivity() {
        return (RegistrationActivity)activity;
    }
}