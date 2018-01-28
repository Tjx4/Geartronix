package co.za.geartronix.providers;

public enum DataServiceProvider {

    userVerify("rest/services/v1/apps/login/userVerify/"),
    onlineImagePath("rest/services/img.png"),
    gallery("rest/services/v1/apps/gallery/images/"),
    login("rest/services/v1/apps/login/loginUser/"),
    forgotpassword("rest/services/v1/apps/forgotpassword/sendResetCode/"),
    registration("rest/services/v1/apps/registration/registerUser/"),
    sendReferral("rest/services/v1/apps/referral/referUser/"),
    askQuestion("rest/services/v1/apps/questions/askQuestion/"),
    getQuestions("rest/services/v1/apps/questions/getQuestion/"),
    getServices("rest/services/v1/apps/services/getServices/"),
    requestService("rest/services/v1/apps/services/requestService/");

    private String path;

    DataServiceProvider(String path) {
        this.path = path;
    }

    public String getPath() {
        return path + "index.php";
    }
}
