package co.za.geartronix.presenters;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ReferralActivity;
import co.za.geartronix.views.IReferralView;

public class ReferralPresenter extends BaseAppActivityPresenter implements IReferralPresenter {

    private boolean isRequestingContacts, isSendReferral;
    private String contactNumber, contactName;
    private EditText contactNumberTxt, contactNameTxt;

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

        switch (clickedViewId){
            case R.id.imgGetContact:
                isRequestingContacts = true;
                break;
            case R.id.btnReferral:
                isSendReferral = true;
                break;
        }

        new DoAsyncCall().execute();
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

    }

    @Override
    public void sendReferral() {
        contactNumber = contactNumberTxt.getText().toString().trim();
        contactName = contactNameTxt.getText().toString().trim();

        if(contactName.isEmpty() || contactNumber.isEmpty()){
            showShortToast("Please enter a name and cell number");
            return;
        }

        showShortToast("sendReferral");
    }

    @Override
    protected void beforeAsyncCall() {
        super.beforeAsyncCall();

        if(isRequestingContacts)
            getNumberFromPhoneContacts();
        else if(isSendReferral)
            sendReferral();
        else
            setViews();
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(int actionIndex) throws Exception {
        this.actionIndex = actionIndex;

        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        isRequestingContacts = false;
        isSendReferral = false;
        super.afterAsyncCall(result);
    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    @Override
    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);

        //showShortToast("menuOptionSelected");
    }

    @Override
    public void goToCurrentAppActivity() {
        goToReferrals();
    }
}