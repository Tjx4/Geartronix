package co.za.geartronix.activities;

import android.view.KeyEvent;

import co.za.geartronix.presenters.FirstTimePresenter;
import co.za.geartronix.views.FirstTimeView;

public class FirstTimeActivity extends BaseOverflowMenuActivity implements FirstTimeView {

    @Override
    public void setPresenter() {
        presenter = new FirstTimePresenter(this);
    }

    @Override
    public FirstTimePresenter getPresenter() {
        return (FirstTimePresenter)presenter;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}