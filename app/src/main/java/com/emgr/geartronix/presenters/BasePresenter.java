package com.emgr.geartronix.presenters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;

public abstract class BasePresenter {

    protected Context context;
    protected String BASE_LOG = "base_log";
    public BaseActivity activity;

    protected void comonOnCreate(int contentView) {
        activity.setContentView(contentView);
    }

    protected void slideInActivity() {
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    protected ActionBar basicActionBarConfiguration(String title) {
        ActionBar ab = this.activity.getSupportActionBar();
        ab.setTitle(title);

        return ab;
    }

    protected void goToActivity(Class activity) {
        Intent i = new Intent(context, activity);
        this.activity.startActivity(i);
    }

    protected void goToActivityWithPayload(Class activity, Bundle bundle) {
        Bundle payload = bundle;
        Intent i = new Intent(context, activity);
        i.putExtra("payload", payload);
        this.activity.startActivity(i);
    }

    protected void showLongToast(String message){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }
    protected void showShortToast(String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected AlertDialog.Builder setupBasicMessage(String message, String title){

        AlertDialog.Builder ab = new AlertDialog.Builder(activity);
        ab.setMessage(message)
                .setTitle(title)
                .setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onPositiveDialogButtonClicked(dialogInterface,  i);
                    }
                });

        return ab;
    }
    private void showMessage(AlertDialog.Builder ab) {
        AlertDialog a = ab.create();
        a.show();
    }

    protected void showSuccessMessage(String message, String title) {
        AlertDialog.Builder ab = setupBasicMessage(message, title);
        ab.setIcon(R.drawable.success_icon);
        showMessage(ab);
    }

    protected void showErrorMessage(String message, String title) {
        AlertDialog.Builder ab = setupBasicMessage(message, title);
        ab.setIcon(R.drawable.error_icon);
        showMessage(ab);
    }

    protected  void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i){}
    protected  void onNagativeButtonClicked(DialogInterface dialogInterface, int i){}
}
