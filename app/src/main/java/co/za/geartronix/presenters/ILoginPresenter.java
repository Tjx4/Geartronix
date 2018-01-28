package co.za.geartronix.presenters;

import android.view.View;
import org.json.JSONException;
import java.io.IOException;
import co.za.geartronix.activities.LoginActivity;

public interface ILoginPresenter extends IBaseAsyncPresenter {
    LoginActivity getActivity();
    String makeUserSignInHttpCall() throws IOException;
    void switchUsers();
    void forgotPassword(View view);
    void setLoginDetails();
    void enterApp();
    void getLinkedUserOREnterUsername();
    void setLinkedUserDetails();
    void setEnterUsername();
    void showUserSelectionView();
    void hideUserSelectionView();
    void handleOnUserSelected(View view);
    void checkAndSignIn();
    String signIn() throws IOException, JSONException;
}
