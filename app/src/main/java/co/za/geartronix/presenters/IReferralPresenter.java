package co.za.geartronix.presenters;

import java.io.IOException;
import co.za.geartronix.activities.ReferralActivity;

public interface IReferralPresenter extends IBaseAsyncPresenter {
    ReferralActivity getActivity();
    void getNumberFromPhoneContacts();
    String sendReferral() throws IOException;
}
