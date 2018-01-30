package co.za.geartronix.presenters;

import java.io.IOException;
import co.za.geartronix.activities.ReferralActivity;

public interface IReferralPresenter extends IBaseAsyncPresenter {
    ReferralActivity getActivity();
    boolean getNumberFromPhoneContacts();
    void showContactList();
    String sendReferral() throws IOException;
    void checkAndSendReferall();
}
