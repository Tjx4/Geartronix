package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.LoginActivity;
import org.json.JSONException;

public interface ILoginPresenter extends IBaseAsyncPresenter {
    LoginActivity getActivity();
    void signIn(View view);
    void setResponseModel(String response) throws JSONException;
    void switchUsers(View view);
    void forgotPassword(View view);
    void setLoginDetails();
    void enterApp();
    void getLinkedUserOREnterUsername();
    void setLinkedUserDetails();
    void setEnterUsername();
}
