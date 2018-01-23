package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.MenuItem;
import co.za.geartronix.R;
import co.za.geartronix.presenters.AskPresenter;
import co.za.geartronix.views.IAskView;

public class AskActivity extends BaseMenuActivity implements IAskView {

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if( itemId == android.R.id.home)
            onBackPressed();
        else
            super.onOptionsItemSelected(item);

        return true;
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