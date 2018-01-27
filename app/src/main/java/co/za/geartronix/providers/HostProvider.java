package co.za.geartronix.providers;

public enum HostProvider {

    localAndroidHost("http://localhost/geartronix/", "http://10.0.2.2:8888/geartronix/"),
    localHost("http://192.168.8.100:8888/geartronix/", "http://192.168.8.100:8888/geartronix/"),
    demoHost("http://emilygracetechnologies.com/demos/geartronix/", "http://192.168.91.2/"),
    liveHost("http://", "http://");

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