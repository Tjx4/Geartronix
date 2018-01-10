package co.za.geartronix.providers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public enum Permissions {

    internet("Internet", Manifest.permission.INTERNET),
    readStorage("Read writeStorage", Manifest.permission.READ_EXTERNAL_STORAGE),
    writeStorage("Write writeStorage", Manifest.permission.WRITE_EXTERNAL_STORAGE);

    String permissionKey;
    String permission;

    Permissions(String permissionKey, String permission) {
        this.permissionKey = permissionKey;
        this.permission = permission;
    }

    public String getPermissionKey() {
        return permissionKey;
    }
    public String getPermission() {
        return permission;
    }

}
