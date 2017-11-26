package com.emgr.geartronix.models;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeModel extends BaseModel{

    private String session;
    private String message;
    private int userId;
    private String user;

    @Override
    public void setModel(JSONObject response) throws JSONException {
        super.setModel(response);
        setUserId(response.getInt("userId"));
        setUser(response.getString("user"));
        setMessage(response.getString("message"));
        setSuccessful(response.getBoolean("isSuccessful"));
        setSession(response.getString("session"));
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
