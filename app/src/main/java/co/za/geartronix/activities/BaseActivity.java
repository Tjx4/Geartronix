package co.za.geartronix.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import co.za.geartronix.presenters.BaseAsyncPresenter;
import co.za.geartronix.presenters.BasePresenter;

public abstract class BaseActivity extends AppCompatActivity {
    public BasePresenter presenter;
    private int menu;
    private ViewGroup activityViews;

    public void onViewClickedEvent(View view) {
        getPresenter().handleAsyncButtonClickedEvent(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.outOfFocus = false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.slideOutActivity();
        presenter.outOfFocus = true;

        getPresenter().handleBackButtonPressed();
    }

    public abstract BaseAsyncPresenter getPresenter();
    public abstract void setPresenter();

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }
}