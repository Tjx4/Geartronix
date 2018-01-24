package co.za.geartronix.presenters;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import java.io.IOException;

import co.za.geartronix.activities.RegistrationActivity;

public interface IRegistrationPresenter extends IBaseAsyncPresenter {
    RegistrationActivity getActivity();
    void setRegProperties();
    void togglePasswordFieldView(EditText passwordTxt, ImageButton toggleIcon);
    String registerUser() throws IOException;
    void validateRegistrationInfo(View view);
}
