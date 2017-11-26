package com.emgr.geartronix.models;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseModel {

    public boolean isSuccessful;
    public String message;
    public String responseCode;

    public String setResponse() {
        return response;
    }

    public void setResponse(JSONObject response) {
        this.response = response.toString();
    }

    protected void setModel(JSONObject response) throws JSONException {
        setResponse(response);
    }

    private String response;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

}
