package co.za.geartronix.presenters;

import android.app.Notification;
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
import co.za.geartronix.models.MessageModel;
import co.za.geartronix.providers.ChatMessage;
import co.za.geartronix.views.IMessagesView;

import static android.view.MenuItem.SHOW_AS_ACTION_ALWAYS;
import static android.view.MenuItem.SHOW_AS_ACTION_WITH_TEXT;

public class MessagesPresenter extends BaseMenuPresenter implements IMessagesPresenter {

    private MessageModel responseModel;
    private ListView listView;
    private View btnSend;
    private EditText messageTxt;
    boolean isMine = true;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;
    private MenuItem recipientPic, recipientTyping;
    private Menu menuView;

    public MessagesPresenter(IMessagesView iMessagesView) {
        super((BaseActivity)iMessagesView);
        setDependanciesNoActionBar(R.layout.activity_home);
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
        chatMessages = new ArrayList<>();
        adapter = new MessageAdapter(getActivity(), R.layout.item_chat_left, chatMessages);
        listView.setAdapter(adapter);
    }

    public void setMenuInstance(Menu menu) {
        menuView = menu;
        recipientTyping = menuView.getItem(0);

        recipientPic = menuView.getItem(1);
        recipientPic.setIcon(R.drawable.profpic2);
    }

    private void setTyping() {
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
            //add message to list
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
    public void goToCurrentAppActivity() {
        goToMessages();
    }
}