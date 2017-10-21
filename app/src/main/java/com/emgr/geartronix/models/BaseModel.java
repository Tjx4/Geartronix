package com.emgr.geartronix.models;

import android.content.pm.PackageInstaller;

public class BaseModel {

    private boolean isSuccessful;
    public String message;
    private String responseCode;
    private PackageInstaller.Session session;

    public Object getDefault(Object property) {
        return property;
    }

    public void setDefault(Object property, Object value) {
        property = value;
    }


    /*
    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public PackageInstaller.Session getSession() {
        return session;
    }

    public void setSession(PackageInstaller.Session session) {
        this.session = session;
    }
    */
}
