package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.MessagesActivity;
import co.za.geartronix.adapters.MessageAdapter;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.MessageModel;
import co.za.geartronix.providers.ChatMessage;
import co.za.geartronix.providers.MessagesCategoryProvider;
import co.za.geartronix.views.IMessagesView;
import static android.view.MenuItem.SHOW_AS_ACTION_ALWAYS;

public class MessagesPresenter extends BaseMenuPresenter implements IMessagesPresenter {

    private MessageModel responseModel;
    private ListView listView;
    private View btnSend;
    private EditText messageTxt;
    private boolean isMine = true;
    private int recipient;
    private int category;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;
    private MenuItem recipientPic, recipientTyping;

    public MessagesPresenter(IMessagesView iMessagesView) {
        super((BaseActivity)iMessagesView);
        setDependanciesNoActionBar(R.layout.activity_messages);
        pageTitle = getActivity().getString(R.string.messages);
        setMenuDependencies(getActivity(), getPageTitle(), R.layout.content_messages );
        setViews();
        initMessages();
    }

    public MessagesPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.message_icon);
        setDisplayName(activity.getString(R.string.messages));
    }

    @Override
    protected void beforeAsyncCall() {

    }

    @Override
    public void setViews() {
        parentLayout = getMainLayout();
        listView = parentLayout.findViewById(R.id.list_msg);
        btnSend = parentLayout.findViewById(R.id.btnSendMessage);
        messageTxt = parentLayout.findViewById(R.id.txtMessage);
    }

    protected void initMessages() {

        Bundle extras = getActivity().getIntent().getExtras();

        if(extras != null)
            category = extras.getInt(Constants.TYPEID);

        chatMessages = new ArrayList<>();
        adapter = new MessageAdapter(getActivity(), R.layout.item_chat_left, chatMessages);
        listView.setAdapter(adapter);
    }

    @Override
    public void configureActionBarItems(Menu menu) {
        super.configureActionBarItems(menu);
        recipientTyping = menuView.getItem(0);
        recipientPic = menuView.getItem(1);
        recipientPic.setIcon(R.drawable.profpic2);

        toolbar.setTitle(" Username");
    }

    @Override
    public void setTyping() {
        recipientTyping.setVisible(true);
        recipientTyping.setTitle(getActivity().getResources().getString(R.string.typing));
        recipientTyping.setShowAsAction(SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    public boolean handleNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_converastions:
                goToConversation();
                break;
            case R.id.action_inbox:
                goToInbox();
                break;
            case R.id.action_outbox:
                goToOutbox();
                break;
            case R.id.action_block:
                blockUser(2);
                break;
        }

        return super.handleNavigationItemSelected(item);
    }

    @Override
    public void handleViewClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    @Override
    public MessagesActivity getActivity() {
        return (MessagesActivity)activity;
    }

    @Override
    protected void postAnimation(View view) {
        int viewId = view.getId();

        if(viewId == R.id.btnSendMessage)
            addNewMessage();

        setTyping();

    }

    @Override
    public void addNewMessage() {

        String message = messageTxt.getText().toString().trim();

        if (!message.isEmpty()) {
            //add responseMessage to list
            ChatMessage chatMessage = new ChatMessage(messageTxt.getText().toString(), isMine);

            chatMessages.add(chatMessage);
            adapter.notifyDataSetChanged();
            messageTxt.setText("");

            if (isMine) {
                isMine = false;
            } else {
                isMine = true;
            }
        }
    }

    @Override
    public void goToConversation() {
        showShortToast("goToConversation");
    }

    @Override
    public void goToOutbox() {
        showShortToast("goToOutbox");
    }

    @Override
    public void goToInbox() {
        showShortToast("goToInbox");
    }

    @Override
    public void blockUser(int userid) {
        showShortToast("blockUser");
    }

    @Override
    public void showTypingToRecipient() {
        showShortToast("show Typing to recipient");
    }

    @Override
    public void goToCurrentAppActivity() {
        goToMessages();
    }

    @Override
    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);
    }

    @Override
    protected void showInstructions() {
        showShortToast("Show instructions on how to use messages activity");
    }
}