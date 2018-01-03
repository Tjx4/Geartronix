package co.za.geartronix.presenters;

import android.view.View;

import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.MessagesActivity;
import co.za.geartronix.views.IMessagesView;

public class MessagesPresenter extends BaseMenuPresenter implements IMessagesPresenter{

    public MessagesPresenter(IMessagesView iMessagesView) {
        super((BaseActivity)iMessagesView);
    }

    public MessagesPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity);
        setProperties(baseActivity, index);
        setIcon(R.mipmap.message_icon);
        setDisplayName(activity.getString(R.string.messages));
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
    protected void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    public void handleViewClickedEvent(View view) {

    }

    @Override
    public void setViews() {

    }

    @Override
    public MessagesActivity getActivity() {
        return null;
    }
}
