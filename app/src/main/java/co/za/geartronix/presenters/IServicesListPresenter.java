package co.za.geartronix.presenters;

import org.json.JSONException;
import java.io.IOException;
import co.za.geartronix.activities.ServicesListActivity;

public interface IServicesListPresenter extends IBaseAsyncPresenter {
    ServicesListActivity getActivity();
    void showServices();
    void setSelectedService(int id);
    String makeServiceRequestHttpCall(int serviceId) throws IOException;
    String makeServicesListHttpCall() throws IOException;
    String requestGeneralCheckup() throws IOException;
    String checkServicesUpdate() throws IOException, JSONException;
    String getServices() throws IOException, JSONException;
    String requestService() throws IOException, JSONException;
}
