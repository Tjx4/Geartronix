package co.za.geartronix.activities;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import co.za.geartronix.presenters.BasePresenter;

public abstract class BaseActivity extends AppCompatActivity {
    public BasePresenter presenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //presenter.activity = null;
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
    }

}