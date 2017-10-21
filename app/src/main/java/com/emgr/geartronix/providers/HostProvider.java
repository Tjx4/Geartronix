package com.emgr.geartronix.providers;

public enum HostProvider {

    localHost("http://localhost/", "http://192.168.225.2/"),
    onlineHost("http://demos.emilygracetechnologies.com/", "http://192.168.91.2/");

    private String url;
    private String ip;

    HostProvider(String url, String ip) {
        this.url = url;
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public String getIp() {
        return ip;
    }

}
