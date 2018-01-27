package co.za.geartronix.models;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginModel extends UserModel{

    public String session;
    public String userName;
    public int id;

    @Override
    public void setModel(JSONObject responseJson) throws JSONException {
       super.setModel(responseJson);
        setUserName(responseJson.getString("name"));
        setId(responseJson.getInt("id"));
        //setSession(responseJson.getString("session"));
    }

    public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    }

    public int getUserId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}