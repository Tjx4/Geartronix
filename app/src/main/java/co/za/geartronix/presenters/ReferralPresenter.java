package co.za.geartronix.presenters;

import android.view.MenuItem;
import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ReferralActivity;
import co.za.geartronix.views.IReferralView;

public class ReferralPresenter  extends BaseAppActivityPresenter implements IReferralPresenter {


    public ReferralPresenter(IReferralView iReferralView) {
        super((BaseActivity)iReferralView);
        setDependanciesChildActivities(R.layout.activity_referral);
        currentActionBar.setTitle(" "+activity.getString(R.string.referral));
        setViews();
        new DoAsyncCall().execute();
    }

    public ReferralPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.referral_icon);
        setDisplayName(baseActivity.getString(R.string.referral));
    }

    @Override
    protected void postAnimation(View view) {
        showShortToast("View clicked");
    }

    @Override
    public void handleViewClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    @Override
    public void setViews() {
//Refer someone to geartronix and help them get the same great service you did or heard about, plus earn points and get free services like sofware upgrades
    }

    @Override
    public ReferralActivity getActivity() {
        return (ReferralActivity)activity;
    }

    @Override
    protected void beforeAsyncCall() {

    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

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