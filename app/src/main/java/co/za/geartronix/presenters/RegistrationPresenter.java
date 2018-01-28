package co.za.geartronix.presenters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.RegistrationActivity;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.MemberModel;
import co.za.geartronix.models.NamesModel;
import co.za.geartronix.models.RegistrationModel;
import co.za.geartronix.models.UserModel;
import co.za.geartronix.providers.ContactDetailsProvider;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.IRegistrationView;

public class RegistrationPresenter extends BaseSlideMenuPresenter implements IRegistrationPresenter {

    private UserModel user;
    private RegistrationModel registrationModel;
    private String title;
    private char gender = 0;
    private EditText nametxt, cityTxt, cellTxt, emailTxt, passwordTxt, passwordConfirmationTxt;
    private ImageView genderImg;

    public RegistrationPresenter(IRegistrationView iRegistrationView) {
        super((BaseActivity)iRegistrationView);
        setDependanciesNoActionBar(R.layout.activity_registration);
        title = getActivity().getString(R.string.create_account);
        setSlideMenuDependencies(getActivity(), getPageTitle(), R.layout.content_registration);
        setViews();

        permissionProvider.requestPhoneStatePermission();
    }

    @Override
    public String getPageTitle() {
        return title;
    }


    @Override
    public String makeRegistrationHttpCall() throws IOException {

        String service = DataServiceProvider.registration.getPath();
        String url = environment + service;

        Bundle payload = new Bundle();
        payload.putString("name", user.getNames().getFirstName());
        payload.putString("gender", user.getGender()+"");
        payload.putString("password", user.getPassword());
        payload.putString("city", user.getCity());
        payload.putString("email", user.getContactDetailsProvider().getEmails()[0]);
        payload.putString("cellNumber", user.getContactDetailsProvider().getContactNumbers()[0]);

        return new HttpConnectionProvider(payload).makeCallForData(url, "GET", true, true, httpConTimeout);

    }

    @Override
    public String registerUser() throws JSONException, IOException {
        registrationModel = new RegistrationModel();
        String response = getRemoteJson(actionIndex);
        registrationModel.setModel(new JSONObject(response));
        return response;
    }


    @Override
    protected String getRemoteJson(int methodIndex) throws IOException {
        if (methodIndex == 0)
            return makeRegistrationHttpCall();
        else
            return null;
    }

    @Override
    protected void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i) {
//===========================
        if(registrationModel == null)
            return;

        if(registrationModel.isSuccessful) {
            Bundle extras = new Bundle();
            int regUserId = sqLiteProvider.getLastUser().getId();
            extras.putInt(Constants.USERID, regUserId);
            goToLogin(extras);
        }
    }

    @Override
    protected void beforeAsyncCall() {
        super.beforeAsyncCall();
    }

    @Override
    protected Object doAsyncOperation(int actionIndex) throws Exception {
        this.actionIndex = actionIndex;
        return registerUser();
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        if(registrationModel.isSuccessful) {
            addUserToDataBase(user);
            showSuccessMessage(registrationModel.getResponseMessage(), getActivity().getString(R.string.success));
        }
        else {
            showErrorMessage(registrationModel.getResponseMessage(), getActivity().getString(R.string.error));
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
    protected void postAnimation(View view) {
        setRegProperties();

        if (view.getId() == R.id.imgBtnPasswordViewtoggle) {
            togglePasswordFieldView(passwordTxt, (ImageButton) view);
            return;
        } else if (view.getId() == R.id.imgBtnConfirmPasswordViewtoggle) {
            togglePasswordFieldView(passwordConfirmationTxt, (ImageButton) view);
            return;
        } else {
            validateRegistrationInfo(view);
        }
    }

    @Override
    public void validateRegistrationInfo(View view) {

        if(!isValidName(user.getNames().getFirstName()))
            showErrorMessage(getActivity().getString(R.string.username_error), getActivity().getString(R.string.error));
        else if(!isValidCell(user.getContactDetailsProvider().getContactNumbers()[0]))
            showErrorMessage(getActivity().getString(R.string.cell_error), getActivity().getString(R.string.error));
        else if(user.getContactDetailsProvider().getEmails()[0].isEmpty() && !isValidEmail(user.getContactDetailsProvider().getEmails()[0]))
            showErrorMessage(getActivity().getString(R.string.cell_error), getActivity().getString(R.string.error));
        else if(!isValidGender(user.getGender()))
            showErrorMessage(getActivity().getString(R.string.gender_error), getActivity().getString(R.string.error));
        else if(!isValidPassword(user.getPassword()))
            showErrorMessage(getActivity().getString(R.string.password_error), getActivity().getString(R.string.error));
        else if(!isMatchPasswords(user.getPassword(), passwordConfirmationTxt.getText().toString()))
            showErrorMessage(getActivity().getString(R.string.password_match_error), "Password error");
        else
            new DoAsyncCall().execute(0);
    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {
        super.handleAsyncButtonClickedEvent(view);

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

        nametxt.setText("Tshepo");
        cellTxt.setText("0823835792");
        emailTxt.setText("rocboyt@gmail.com");
        cityTxt.setText("Pretoria");
        passwordTxt.setText("Tl@12345");
        passwordConfirmationTxt.setText("Tl@12345");

    }

    @Override
    public RegistrationActivity getActivity() {
        return (RegistrationActivity)activity;
    }
}