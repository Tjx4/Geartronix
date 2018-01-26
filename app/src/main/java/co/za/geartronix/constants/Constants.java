package co.za.geartronix.constants;

import co.za.geartronix.providers.HostProvider;

public class Constants {
    public static final String CURRENTENVIRONMENT = HostProvider.demoHost.getUrl();
    public static final String PAYLOAD = "payload";
    public static final String FEATURE_ERROR = "feature_error";
    public static final String USERID = "user_id";
    public static final String USECODE = "useCode";
    public static final String TYPEID = "typeId";
    public static final String TITLE = "title";
    public static final String LAYOUT = "layout";
    public static final int PROFILEID = 000;
    public static final int BOOKSERVICEID = 001;
    public static final int GALLERYID = 004;
    public static final int CONTACTUSID = 005;
    public static final int CHATID = 006;
    public static final int FINDUSID = 007;
    public static final int MESSAGES = 010;
    public static final int DIAGNOSTICS = 011;
    public static final int REFERRAL = 012;
}