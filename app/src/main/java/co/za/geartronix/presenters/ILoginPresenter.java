package co.za.geartronix.presenters;

import android.view.View;
import co.za.geartronix.activities.LoginActivity;

public interface ILoginPresenter extends IBaseAsyncPresenter {
    LoginActivity getActivity();
    void signIn(View view);
    void switchUsers(View view);
    void forgotPassword(View view);
    void setLoginDetails();
    void enterApp();
    void getLinkedUserOREnterUsername();
    void setLinkedUserDetails();
    void setEnterUsername();
    void showUserSelectionView();
    void handleOnUserSelected(View view);
}
