package co.za.geartronix.activities;

import android.os.Bundle;
import co.za.geartronix.R;
import co.za.geartronix.presenters.AskPresenter;
import co.za.geartronix.views.IAskView;

public class AskActivity extends BaseOverflowMenuActivity implements IAskView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu(R.menu.ask_question_menu);
    }

    @Override
    public void setPresenter() {
        presenter = new AskPresenter(this);
    }

    @Override
    public AskPresenter getPresenter() {
        return (AskPresenter)presenter;
    }

    @Override
    public void onBackPressed() {
        if(getPresenter().imageEnlarged) {
            getPresenter().hideEnlargedImage();
        }
        else {
            super.onBackPressed();
        }
    }
}