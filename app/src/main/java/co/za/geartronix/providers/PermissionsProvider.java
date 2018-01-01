package co.za.geartronix.providers;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
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

    public void requestStoragePermission() {
        if(isbellowMashMellow() )
            return;

        ActivityCompat.requestPermissions(activity,  new String[]{Permissions.writeStorage.getPermission()}, 1);
    }

    public void requestInternetPermission() {
        if(isbellowMashMellow() )
            return;

        ActivityCompat.requestPermissions(activity,  new String[]{Permissions.internet.getPermission()}, 1);
    }

    public boolean isbellowMashMellow() {
        return Build.VERSION.SDK_INT < 23;
    }

}