package co.za.geartronix.models;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginModel extends BaseModel{

    public String session;
    public int userId;
    public String user;
    //public UserModel user;

    @Override
    public void setModel(JSONObject responseJson) throws JSONException {
       super.setModel(responseJson);
        setUserId(responseJson.getInt("userId"));
        setUser(responseJson.getString("user"));
        setSession(responseJson.getString("session"));
    }

    public int getUserId() {  return userId;}
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    }
}
