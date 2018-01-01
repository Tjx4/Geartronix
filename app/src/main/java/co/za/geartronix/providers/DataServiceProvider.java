package co.za.geartronix.providers;

public enum DataServiceProvider {

    userVerify("rest/services/v1/apps/login/userVerify/"),
    onlineImagePath("rest/services/img.png"),
    gallery("rest/services/v1/apps/gallery/images/"),
    login("rest/services/v1/apps/login/loginUser/");

    private String path;

    DataServiceProvider(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
