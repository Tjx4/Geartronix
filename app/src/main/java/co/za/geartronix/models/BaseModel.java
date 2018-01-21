package co.za.geartronix.models;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseModel {

    public boolean isSuccessful;
    public String responseMessage;
    public String responseCode;
    private String responseJson;

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(JSONObject responseJson) {
        this.responseJson = responseJson.toString();
    }

    public void setModel(JSONObject responseJson) throws JSONException {
        setResponseJson(responseJson);
        isSuccessful = responseJson.getBoolean(("isSuccessful"));
        responseMessage = responseJson.getString(("responseMessage"));
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
