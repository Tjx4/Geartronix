package co.za.geartronix.providers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import co.za.geartronix.activities.BaseActivity;

public class PermissionsProvider {

    private BaseActivity activity;

    public PermissionsProvider(BaseActivity activity) {
        this.activity = activity;
    }

    public boolean checkPermissionGranted(String permision) {
//Todo: if lower than mashmellow check the app menifest
        if(isbellowMashMellow() )
            return true;

        return activity.checkSelfPermission(permision) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestWriteStoragePermission() {
        if(isbellowMashMellow() )
            return;

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }


    public void requestReadStoragePermission() {
        if(isbellowMashMellow() )
            return;

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    public void requestInternetPermission() {
        if(isbellowMashMellow() )
            return;

        //ActivityCompat.requestPermissions(activity,  new String[]{Permissions.internet.getPermission()}, 1);

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.INTERNET)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, 1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    public void requestPhoneStatePermission() {
        if(isbellowMashMellow() )
            return;

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {

            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            }
        }
    }


    public boolean isbellowMashMellow() {
        return Build.VERSION.SDK_INT < 23;
    }

}