package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.AskActivity;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.adapters.MessageAdapter;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.providers.ChatMessage;
import co.za.geartronix.views.IAskView;

public class AskPresenter  extends BaseAppActivityPresenter implements IAskPresenter {

    private ListView listView;
    private View btnSend;
    private EditText messageTxt;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;
    private boolean isMine = true;

    public AskPresenter(IAskView iGalleryView) {
        super((BaseActivity)iGalleryView);
        setDependanciesChildActivities(R.layout.activity_ask);
        currentActionBar.setTitle(" "+activity.getString(R.string.messages));
        setViews();
        initMessages();
    }

    public AskPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.message_icon);
        setDisplayName(activity.getString(R.string.messages));
    }

    @Override
    public void setViews() {
        listView = (ListView) getActivity().findViewById(R.id.list_msg);
        btnSend = getActivity().findViewById(R.id.btnSendMessage);
        messageTxt = (EditText) getActivity().findViewById(R.id.txtMessage);
    }

    @Override
    protected void postAnimation(View view) {
        int viewId = view.getId();

        if(viewId == R.id.btnSendMessage)
            addNewMessage();
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
    public AskActivity getActivity() {
        return (AskActivity)activity;
    }

    @Override
    protected void beforeAsyncCall() {

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
    public void handleAsyncButtonClickedEvent(View view) {
        blinkView(view, 30, 70);
    }

    protected void initMessages() {

        chatMessages = new ArrayList<>();
        adapter = new MessageAdapter(getActivity(), R.layout.item_chat_left, chatMessages);
        listView.setAdapter(adapter);
    }
}