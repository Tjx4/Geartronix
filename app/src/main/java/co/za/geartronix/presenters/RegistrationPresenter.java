package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import org.json.JSONObject;
import java.io.IOException;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.RegistrationActivity;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.MemberModel;
import co.za.geartronix.models.NamesModel;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.providers.ContactDetailsProvider;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.IRegistrationView;

public class RegistrationPresenter extends BaseSlideMenuPresenter implements IRegistrationPresenter {

    private UserModel user;
    private String title;
    private char gender = 0;
    private EditText nametxt, cityTxt, cellTxt, emailTxt, passwordTxt, passwordConfirmationTxt;
    private ImageView genderImg;

    public RegistrationPresenter(IRegistrationView iRegistrationView) {
        super((BaseActivity)iRegistrationView);
        setDependanciesNoActionBar(R.layout.activity_registration);
        title = getActivity().getString(R.string.create_account);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.content_registration);
        setViews();
        permissionProvider.requestPhoneStatePermission();
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
        payload.putString("name", user.getNames().getFirstName());
        payload.putString("password", user.getPassword());
        payload.putString("city", user.getCity());
        payload.putString("email", user.getContactDetailsProvider().getEmails()[0]);
        payload.putString("cellNumber", user.getContactDetailsProvider().getContactNumbers()[0]);

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
        String response = getRemoteJson();
        user.setModel(new JSONObject(response));

        return response;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        if(user.isSuccessful) {
            addUserToDataBase(user);
            Bundle extras = new Bundle();
            int regUserId = sqLiteProvider.getLastUser().getId();
            extras.putInt(Constants.USERID, regUserId);
            goToLogin(extras);
        }
        else {
            showErrorMessage(user.getResponseMessage(), getActivity().getString(R.string.error));
        }

        super.afterAsyncCall(result);
    }

    @Override
    public void setRegProperties() {
        user = new UserModel();
        NamesModel names = new NamesModel();
        names.setFirstName(nametxt.getText().toString());
        user.setNames(names);
        user.setPassword(passwordTxt.getText().toString());
        user.setCity(cityTxt.getText().toString());
        user.setGender(gender);

        ContactDetailsProvider contactDetails = new ContactDetailsProvider();
        contactDetails.setContactNumbers(new String[]{cellTxt.getText().toString()});
        contactDetails.setEmails(new String[]{emailTxt.getText().toString()});
        user.setContactDetailsProvider(contactDetails);

        MemberModel memberModel = new MemberModel();
        memberModel.setMemberType(0);
        user.setMemberType(memberModel);
    }

    @Override
    public void registerUser() {

    }

    @Override
    protected void postAnimation(View view) {
        setRegProperties();

        if(!isValidName(user.getNames().getFirstName()))
            showErrorMessage(getActivity().getString(R.string.username_error), "Username error");
        else if(!isValidCell(user.getContactDetailsProvider().getContactNumbers()[0]))
            showErrorMessage(getActivity().getString(R.string.cell_error), "Cell number error");
        else if(user.getContactDetailsProvider().getEmails()[0].isEmpty() && !isValidEmail(user.getContactDetailsProvider().getEmails()[0]))
            showErrorMessage(getActivity().getString(R.string.cell_error), "Cell number error");
        else if(!isValidGender(user.getGender()))
            showErrorMessage(getActivity().getString(R.string.gender_error), "Gender error");
        else if(!isValidPassword(user.getPassword()))
            showErrorMessage(getActivity().getString(R.string.password_error), "Password error");
        else if(!isMatchPasswords(user.getPassword(), passwordConfirmationTxt.getText().toString()))
            showErrorMessage(getActivity().getString(R.string.password_match_error), "Password error");
        else
            new DoAsyncCall().execute();
    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {
        int viewId = view.getId();

        if(viewId == R.id.rdoFemale)
            setGender('f');
        else if(viewId == R.id.rdoMale)
            setGender('m');
        else
            blinkView(view, 30, 70);
    }

    public void setGender(char gender) {
        this.gender = gender;

        if(gender == 'f'){
            genderImg.setImageResource(R.drawable.female_icon);
        }
        else {
            genderImg.setImageResource(R.drawable.male_icon);
        }
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
        genderImg = parentLayout.findViewById(R.id.imgGender);

        /*
        nametxt.setText("Rob");
        cellTxt.setText("0842630120");
        emailTxt.setText("rob@gmail.com");
        cityTxt.setText("Pretoria");
        passwordTxt.setText("Tl@0793079399");
        passwordConfirmationTxt.setText("Tl@0793079399");
        */
    }

    @Override
    public RegistrationActivity getActivity() {
        return (RegistrationActivity)activity;
    }
}