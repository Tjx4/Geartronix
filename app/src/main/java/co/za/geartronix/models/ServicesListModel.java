package co.za.geartronix.models;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class ServicesListModel extends BaseModel {

    private List<ServiceModel> services;

    public void setModel(JSONObject responseJson) throws JSONException {

    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

}