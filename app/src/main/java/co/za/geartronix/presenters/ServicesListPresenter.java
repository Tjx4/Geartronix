package co.za.geartronix.presenters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ServicesListActivity;
import co.za.geartronix.adapters.ServicesAdapter;
import co.za.geartronix.models.BaseModel;
import co.za.geartronix.models.ServiceModel;
import co.za.geartronix.models.ServiceRequestModel;
import co.za.geartronix.models.ServicesListModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.IServicesListView;

public class ServicesListPresenter extends BaseOverflowMenuPresenter implements IServicesListPresenter {

    private BaseModel baseServicesModel;
    private ServicesListModel serviceListModel;
    private ServiceRequestModel serviceRequestModel;
    private List<ServiceModel> services;
    private ServiceModel selectedService;
    private ListView servicesLst;

    public ServicesListPresenter(IServicesListView iGalleryView) {
        super((BaseActivity)iGalleryView);
        setDependanciesChildActivities(R.layout.activity_services_list);
        currentActionBar.setTitle(" "+getActivity().getString(R.string.services));
        setViews();
        setLoadingText(getActivity().getString(R.string.loading_services));

        new DoAsyncCall().execute(0);
    }

    public ServicesListPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.service_icon);
        setDisplayName(getActivity().getString(R.string.services));
    }

    @Override
    protected void openedStateMethod() {
        requestMenuItem.setVisible(false);
    }

    @Override
    protected void closedStateMethod() {
        requestMenuItem.setVisible(true);
    }

    @Override
    public void setViews() {
        setAsyncViews();
        laodingTxt = (TextView) getActivity().findViewById(R.id.txtLoading);
        servicesLst = (ListView)getActivity().findViewById(R.id.lstServices);
    }

    @Override
    public ServicesListActivity getActivity() {
        return (ServicesListActivity)activity;
    }

    @Override
    public void showServices() {
        services = serviceListModel.getServices();
        ArrayAdapter servicesAdapter = new ServicesAdapter(getActivity(), R.layout.service_item, services);
        servicesLst.setAdapter(servicesAdapter);
    }

    @Override
    protected void checkAndUpdate() {
        isCheckingUpdates = true;
        new DoAsyncCall().execute(0);
    }

    @Override
    protected boolean isCached() {
        return serviceListModel != null && !serviceListModel.getServices().isEmpty();
    }

    @Override
    protected boolean hasUpdate(BaseModel baseModel) {
        ServicesListModel servicesListModel = (ServicesListModel)baseModel;
        List<ServiceModel> currImages = serviceListModel.getServices();
        List<ServiceModel> remoteImages = servicesListModel.getServices();
//TODO: Fix
        boolean isSame = false;
        int indx = 0;
        for(ServiceModel i : currImages){

            if( !i.getServiceDescription().equals( remoteImages.get(indx).getServiceDescription() ) )
                isSame = true;

            indx++;
        }

        //!currImages.equals(remoteImages);
        return isSame;
    }
    @Override
    public void setSelectedService(int id) {
        selectedService = services.get(id - 2);
    }

    @Override
    public String makeServiceRequestHttpCall(int serviceId)throws IOException {
        String service = DataServiceProvider.requestService.getPath();
        String url = environment + service;

        Bundle payload = new Bundle();
        payload.putString("serviceId", serviceId+"");

        return new HttpConnectionProvider(payload).makeCallForData(url, "GET", true, true, httpConTimeout);
    }

    @Override
    public String makeServicesListHttpCall() throws IOException{
        String service = DataServiceProvider.getServices.getPath();
        String url = environment + service;

        return new HttpConnectionProvider().makeCallForData(url, "GET", true, true, httpConTimeout);
    }

    @Override
    public String requestGeneralCheckup()throws IOException {
        return makeServiceRequestHttpCall(1);
    }

    @Override
    protected String getRemoteJson(int methodIndex) throws IOException {
        if (methodIndex == 0)
            return makeServicesListHttpCall();
        else if (methodIndex == 1)
            return makeServiceRequestHttpCall(selectedService.getId());
        else if (methodIndex == 2)
            return requestGeneralCheckup();
        else
            return null;
    }

    @Override
    public String checkServicesUpdate() throws IOException, JSONException {
        ServicesListModel remoteServices = new ServicesListModel();
        String response = getRemoteJson(actionIndex);
        remoteServices.setModel(new JSONObject(response));

        if (hasUpdate(remoteServices) || !isCached())
            cacheProvider.updateServices(remoteServices);

        return response;
    }

    @Override
    public String getServices() throws IOException, JSONException {
        serviceListModel = cacheProvider.getServices();
        String response = "";

        if(!isCached()) {
            serviceListModel = new ServicesListModel();
            response = getRemoteJson(actionIndex);
            serviceListModel.setModel(new JSONObject(response));
            cacheProvider.updateServices(serviceListModel);
        }

        baseServicesModel = serviceListModel;
        return response;
    }

    @Override
    public String requestService() throws IOException, JSONException {
        serviceRequestModel = new ServiceRequestModel();
        String response = getRemoteJson(actionIndex);
        serviceRequestModel.setModel(new JSONObject(response));
        baseServicesModel = serviceRequestModel;
        return response;
    }

    @Override
    protected void beforeAsyncCall() {
        if(isCheckingUpdates)
            return;

        super.beforeAsyncCall();
    }

    @Override
    protected Object doAsyncOperation(int actionIndex) throws Exception {
        this.actionIndex = actionIndex;
        if(isCheckingUpdates) {
            return checkServicesUpdate();
        }

        if (actionIndex == 0) {
            return getServices();
        }
        else{
            return requestService();
        }
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        if(isCheckingUpdates) {
            isCheckingUpdates = false;
            return;
        }

        if(baseServicesModel.isSuccessful) {

            if(actionIndex == 0) {
                showServices();
            }
            else {
                onPostServicesRequest();
            }

            if(isCached())
                checkAndUpdate();
        }
        else {
            showErrorMessage(baseServicesModel.getResponseMessage(), getActivity().getString(R.string.error));
        }

        super.afterAsyncCall(result);
    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {
        toggleSubContent(view);
        setSelectedService(view.getId());
    }

    public void onPostServicesRequest() {
        showSuccessMessage(baseServicesModel.responseMessage, getActivity().getString(R.string.success));
    }

    @Override
    protected void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i) {
        if(clickedViewId == R.id.action_request_service)
            new DoAsyncCall().execute(1);
        else if(clickedViewId == R.id.action_general_checkup)
            new DoAsyncCall().execute(2);
    }

    @Override
    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);
        clickedViewId = item.getItemId();

        if(isNotBaseMenuActions() ) {

            String confirmationMessage = getActivity().getString(R.string.general_checkup_diagostic_message) +" "+ getActivity().getString(R.string.continue_message);

            if(clickedViewId == R.id.action_request_service)
                confirmationMessage = "The service you are requesting is \""+selectedService.getService()+"\" "+ getActivity().getString(R.string.continue_message);

                showConfirmMessage(confirmationMessage, getActivity().getResources().getString(R.string.confirm), true, false);
        }
    }

    @Override
    protected void showInstructions() {
        showShortToast("Show instructions on how to use services activity");
    }

    public void configureActionBarItems(Menu menu) {
        requestMenuItem = menu.getItem(0);
    }
}