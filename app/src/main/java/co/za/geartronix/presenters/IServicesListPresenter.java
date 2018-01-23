package co.za.geartronix.presenters;

import java.io.IOException;

import co.za.geartronix.activities.ServicesListActivity;

public interface IServicesListPresenter extends IBaseAsyncPresenter {
    ServicesListActivity getActivity();
    void showServices();
    void setSelectedService(int id);
    String requestService(int serviceId) throws IOException;
    String requestServices() throws IOException;
    String requestGeneralCheckup() throws IOException;
}
