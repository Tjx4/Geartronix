package com.emgr.geartronix.presenters;

import android.view.View;

import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.views.IMessagesView;

public class MessagesPresenter extends BaseAppActivityPresenter {

    public MessagesPresenter(IMessagesView iMessagesView) {
        super((BaseActivity)iMessagesView);
    }

    public MessagesPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity);
        setProperties(baseActivity);
        setIcon(R.mipmap.message_icon);
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
}
