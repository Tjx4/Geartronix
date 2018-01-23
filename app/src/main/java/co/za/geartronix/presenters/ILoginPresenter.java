package co.za.geartronix.presenters;

import android.view.View;
import java.io.IOException;
import co.za.geartronix.activities.LoginActivity;

public interface ILoginPresenter extends IBaseAsyncPresenter {
    LoginActivity getActivity();
    String signIn() throws IOException;
    void switchUsers();
    void forgotPassword(View view);
    void setLoginDetails();
    void enterApp();
    void getLinkedUserOREnterUsername();
    void setLinkedUserDetails();
    void setEnterUsername();
    void showUserSelectionView();
    void handleOnUserSelected(View view);
    void checkAndSignIn();
}
