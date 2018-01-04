package co.za.geartronix.activities;

import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.presenters.MessagesPresenter;
import co.za.geartronix.views.IMessagesView;

public class MessagesActivity extends BaseAsyncActivity implements IMessagesView, NavigationView.OnNavigationItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public MessagesPresenter getPresenter() {
        return (MessagesPresenter)presenter;
    }

    @Override
    public void setPresenter() {
        presenter = new MessagesPresenter(this);
    }

    @Override
    public void onViewClickedEvent(View view) {
        getPresenter().handleViewClickedEvent(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        getPresenter().setMenuInstance(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().menuOptionSelected(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getPresenter().handleBackButtonPressed();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return getPresenter().handleNavigationItemSelected(item);
    }
}
