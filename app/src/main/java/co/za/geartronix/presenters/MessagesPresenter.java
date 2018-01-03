package co.za.geartronix.presenters;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.MessagesActivity;
import co.za.geartronix.adapters.MessageAdapter;
import co.za.geartronix.models.MessageModel;
import co.za.geartronix.providers.ChatMessage;
import co.za.geartronix.views.IMessagesView;

public class MessagesPresenter extends BaseMenuPresenter implements IMessagesPresenter {

    private MessageModel responseModel;
    private ListView listView;
    private View btnSend;
    private EditText editText;
    boolean isMine = true;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;

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
        btnSend = parentLayout.findViewById(R.id.btn_chat_send);
        editText = parentLayout.findViewById(R.id.msg_type);
    }

    protected void initMessages() {
        chatMessages = new ArrayList<>();
        adapter = new MessageAdapter(getActivity(), R.layout.item_chat_left, chatMessages);
        listView.setAdapter(adapter);
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

    }

    @Override
    public MessagesActivity getActivity() {
        return (MessagesActivity)activity;
    }

    @Override
    public void goToCurrentAppActivity() {
        goToMessages();
    }
}