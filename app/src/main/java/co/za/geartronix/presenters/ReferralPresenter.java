package co.za.geartronix.presenters;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ReferralActivity;
import co.za.geartronix.views.IReferralView;

public class ReferralPresenter extends BaseAppActivityPresenter implements IReferralPresenter {

    private String contactNumber, contactName;
    private EditText contactNumberTxt, contactNameTxt;

    public ReferralPresenter(IReferralView iReferralView) {
        super((BaseActivity)iReferralView);
        setDependanciesChildActivities(R.layout.activity_referral);
        currentActionBar.setTitle(" "+activity.getString(R.string.referral));
        new DoAsyncCall().execute();
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
                getNumberFromPhoneContacts();
                break;
            case R.id.btnReferral:
                sendReferral();
                break;
        }

    }

    @Override
    public void handleViewClickedEvent(View view) {
        blinkView(view, 30, 70);
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
        showShortToast("getNumberFromPhoneContacts");
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
        setViews();
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
        setViews();
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        super.afterAsyncCall(result);
    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }

    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);

        //showShortToast("menuOptionSelected");
    }

    @Override
    public void goToCurrentAppActivity() {
        goToReferrals();
    }
}