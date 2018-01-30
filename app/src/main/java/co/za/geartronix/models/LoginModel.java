package co.za.geartronix.models;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginModel extends BaseModel{

    public String session;
    public UserModel userSigningIn;
    public boolean linkUser;
    public boolean active;

    @Override
    public void setModel(JSONObject responseJson) throws JSONException {
       super.setModel(responseJson);
        JSONObject userJson = new JSONObject("userSigningIn");

        userSigningIn.setId(userJson.getInt("id"));

        NamesModel userNames = new NamesModel();
        userNames.setFirstName(userJson.getString("name"));
        userNames.setSurName(userJson.getString("surname"));
        userSigningIn.setNames(userNames);

        ContactDetailsModel contactDetails = new ContactDetailsModel();

        userJson.getJSONArray("contact_numbers");
        String[] contactNumbers = new String[]{};
        contactDetails.setContactNumbers(contactNumbers);

        userJson.getJSONArray("emails");
        String[] emails = new String[]{};
        contactDetails.setEmails(emails);
        userSigningIn.setContactDetailsProvider(contactDetails);

        userSigningIn.setGender(userJson.getString("gender").charAt(0));
        userSigningIn.setId(userJson.getInt("member_type"));
        setActive(userJson.getBoolean("active"));

        setSession(responseJson.getString("session"));
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    }

    public UserModel getUserSigningIn() {
        return userSigningIn;
    }

    public void setUserSigningIn(UserModel userSigningIn) {
        this.userSigningIn = userSigningIn;
    }

    public boolean isLinkUser() {
        return linkUser;
    }

    public void setLinkUser(boolean linkUser) {
        this.linkUser = linkUser;
    }
}