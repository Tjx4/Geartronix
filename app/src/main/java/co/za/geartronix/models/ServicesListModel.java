package co.za.geartronix.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicesListModel extends BaseModel {

    private List<ServiceModel> services;

    public void setModel(JSONObject responseJson) throws JSONException {
        super.setModel(responseJson);
        JSONArray itemsArray = responseJson.getJSONArray("services");

        services = new ArrayList<>();
        for(int i = 0; i < itemsArray.length(); i++){
            JSONObject itemsAr = (JSONObject)itemsArray.get(i);

            int showService = Integer.parseInt(itemsAr.getString("show_service"));
            if(showService == 0)
                continue;

            ServiceModel currService = new ServiceModel();
            currService.setId(itemsAr.getInt("id"));
            currService.setService(itemsAr.getString("service"));
            currService.setServiceDescription(itemsAr.getString("description"));
            currService.setShow_service(showService);
            services.add(currService);
        }
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

}