package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import org.json.JSONObject;
import java.io.IOException;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ReferralActivity;
import co.za.geartronix.models.ReferralModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.IReferralView;

public class ReferralPresenter extends BaseAppActivityPresenter implements IReferralPresenter {

    private boolean isRequestingContacts;
    private String contactNumber, contactName;
    private EditText contactNumberTxt, contactNameTxt;
    private ReferralModel referralModel;

    public ReferralPresenter(IReferralView iReferralView) {
        super((BaseActivity)iReferralView);
        setDependanciesChildActivities(R.layout.activity_referral);
        currentActionBar.setTitle(" "+activity.getString(R.string.referral));
        setViews();

        permissionProvider.requestPhoneContactPermission();
    }

    public ReferralPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.referral_icon);
        setDisplayName(baseActivity.getString(R.string.referral));
    }

    @Override
    protected void postAnimation(View view) {

        clickedViewId = view.getId();

        switch (clickedViewId) {
            case R.id.imgGetContact:
                new DoAsyncCall().execute(0);
                break;
            case R.id.btnReferral:
                checkAndSendReferall();
                break;
        }
    }

    @Override
    public void checkAndSendReferall() {
        contactName = contactNameTxt.getText().toString();
        contactNumber = contactNumberTxt.getText().toString();

        if(contactName.isEmpty() || contactNumber.isEmpty())
            showShortToast("Please enter name and phone number");
        else
            new DoAsyncCall().execute(1);
    }

    @Override
    public void setViews() {
        contactNumberTxt = (EditText)getActivity().findViewById(R.id.txtContactName);
        contactNameTxt = (EditText)getActivity().findViewById(R.id.txtContactName);
    }

    @Override
    public ReferralActivity getActivity() {
        return (ReferralActivity)activity;
    }

    @Override
    public void getNumberFromPhoneContacts() {
        isRequestingContacts = true;

    }

    @Override
    public String sendReferral() throws IOException {
        String service = DataServiceProvider.sendReferral.getPath();
        String url = environment + service;

        Bundle payload = new Bundle();
        payload.putString("contactName", contactName);
        payload.putString("contactNumber", contactNumber);

        return new HttpConnectionProvider(payload).makeCallForData(url, "GET", true, true, httpConTimeout);
    }

    @Override
    protected String getRemoteJson(int methodIndex) throws IOException {
        if(methodIndex == 0)
            getNumberFromPhoneContacts();
        else if(methodIndex == 1)
            return sendReferral();

            return null;
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
        String response = getRemoteJson(actionIndex);
        referralModel = new ReferralModel();
        referralModel.setModel(new JSONObject(response));
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        if(isRequestingContacts){
            showShortToast("Get contacts from phone book");
            return;
        }
        else
        {
            if(referralModel.isSuccessful) {
                showSuccessMessage(referralModel.responseMessage, getActivity().getString(R.string.success));
            }
            else {
                showErrorMessage(referralModel.responseMessage, getActivity().getString(R.string.error));
            }
        }

        isRequestingContacts = false;
        super.afterAsyncCall(result);
    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    @Override
    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);
    }

    @Override
    public void goToCurrentAppActivity() {
        goToReferrals();
    }
}