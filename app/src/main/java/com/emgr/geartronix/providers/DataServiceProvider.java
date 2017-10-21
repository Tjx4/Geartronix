package com.emgr.geartronix.providers;

public enum DataServiceProvider {

    userVerify("rest/services/v2/apps/login/userVerify/"),
    onlineImagePath("rest/img.png"),
    login("rest/services/av2/apps/login/loginUser/");

    private String path;

    DataServiceProvider(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
