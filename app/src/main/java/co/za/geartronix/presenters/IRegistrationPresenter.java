package co.za.geartronix.presenters;

import android.view.View;
import org.json.JSONException;
import java.io.IOException;
import co.za.geartronix.activities.RegistrationActivity;

public interface IRegistrationPresenter extends IBaseAsyncPresenter {
    RegistrationActivity getActivity();
    void setRegProperties();
    String makeRegistrationHttpCall() throws IOException;
    void validateRegistrationInfo(View view);
    String registerUser() throws JSONException, IOException;
}
