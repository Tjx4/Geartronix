package co.za.geartronix.presenters;

import android.view.View;
import java.io.IOException;
import co.za.geartronix.activities.RegistrationActivity;

public interface IRegistrationPresenter extends IBaseAsyncPresenter {
    RegistrationActivity getActivity();
    void setRegProperties();
    String registerUser() throws IOException;
    void validateRegistrationInfo(View view);
}
