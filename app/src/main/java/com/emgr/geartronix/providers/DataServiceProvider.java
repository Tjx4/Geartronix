package com.emgr.geartronix.providers;

public enum DataServiceProvider {

    userVerify("rest/services/v1/apps/login/userVerify/"),
    onlineImagePath("rest/services/img.png"),
    login("rest/services/v1/apps/login/onLoginButtonClicked/");

    private String path;

    DataServiceProvider(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
