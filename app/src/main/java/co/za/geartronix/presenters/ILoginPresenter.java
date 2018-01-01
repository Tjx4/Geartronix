package co.za.geartronix.presenters;


import android.view.View;
import co.za.geartronix.activities.LoginActivity;
import org.json.JSONException;

public interface ILoginPresenter extends IBaseAsyncPresenter {
    void onLoginButtonClicked(View view);
    void setResponseModel(String response) throws JSONException;
    void onRegisterClicked(View view);
    void onForgotPasswordClicked(View view);
    void setLoginDetails();
    void goToDashBoard();
    LoginActivity getActivity();
    void setViews();
}
