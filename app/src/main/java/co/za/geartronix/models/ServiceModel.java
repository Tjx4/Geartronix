package co.za.geartronix.models;

public class ServiceModel extends BaseModel{
    private String service, serviceDescription;
    private int id, show_service;;

    public int getShow_service() {
        return show_service;
    }

    public void setShow_service(int show_service) {
        this.show_service = show_service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
