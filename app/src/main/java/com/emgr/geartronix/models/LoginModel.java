package com.emgr.geartronix.models;

public class LoginModel extends BaseModel{

    public String session;
    public int userId;
    public String user;

    public int getUserId() {
        return userId;
    }

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
