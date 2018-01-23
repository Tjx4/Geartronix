package co.za.geartronix.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import co.za.geartronix.R;
import co.za.geartronix.presenters.DiagnosticsPresenter;
import co.za.geartronix.views.IDiagnosticsView;

public class DiagnosticsActivity extends BaseOverflowMenuActivity implements IDiagnosticsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter = new DiagnosticsPresenter(this);
    }

    @Override
    public void onViewClickedEvent(View view) {
        getPresenter().handleAsyncButtonClickedEvent(view);
    }

    @Override
    public DiagnosticsPresenter getPresenter() {
        return (DiagnosticsPresenter)presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diagnostics_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if( itemId == android.R.id.home)
            onBackPressed();
        else
            getPresenter().menuOptionSelected(item);

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