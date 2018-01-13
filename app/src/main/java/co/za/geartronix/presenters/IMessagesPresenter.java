package co.za.geartronix.presenters;

import co.za.geartronix.activities.MessagesActivity;

public interface IMessagesPresenter  extends IBaseAsyncPresenter {
    MessagesActivity getActivity();
    void addNewMessage();
    void goToConversation();
    void goToOutbox();
    void goToInbox();
    void blockUser(int userid);
    void showTypingToRecipient();
    void setTyping();
}
