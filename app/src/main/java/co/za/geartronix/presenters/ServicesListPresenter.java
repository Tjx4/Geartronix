package co.za.geartronix.presenters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ServicesListActivity;
import co.za.geartronix.adapters.ServicesAdapter;
import co.za.geartronix.models.ServiceModel;
import co.za.geartronix.models.ServicesListModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.IServicesListView;

public class ServicesListPresenter extends BaseOverflowMenuPresenter implements IServicesListPresenter {

    private ServicesListModel serviceListModel;
    private List<ServiceModel> services;
    private ServiceModel selectedService;
    private ListView servicesLst;

    public ServicesListPresenter(IServicesListView iGalleryView) {
        super((BaseActivity)iGalleryView);
        setDependanciesChildActivities(R.layout.activity_services_list);
        currentActionBar.setTitle(" "+getActivity().getString(R.string.services));
        setViews();

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
    public void setSelectedService(int id) {
        selectedService = services.get(id);
    }

    @Override
    public String requestService(int serviceId)throws IOException {
        String service = DataServiceProvider.requestService.getPath();
        String url = environment + service;

        Bundle payload = new Bundle();
        payload.putInt("serviceId", serviceId);

        String test = new HttpConnectionProvider(payload).makeCallForData(url, "GET", true, true, httpConTimeout);
        return test;
    }

    @Override
    public String requestServices() throws IOException{
        String service = DataServiceProvider.getServices.getPath();
        String url = environment + service;

        return new HttpConnectionProvider().makeCallForData(url, "GET", true, true, httpConTimeout);
    }

    @Override
    public String requestGeneralCheckup()throws IOException {
        return requestService(0);
    }

    @Override
    protected String getRemoteJson(int methodIndex) throws IOException {
        String result = null;

        switch (methodIndex){
            case 0:
                result = requestServices();
                break;
            case 1:
                result = requestService(selectedService.getId());
                break;
            case 2:
                result = requestGeneralCheckup();
                break;
        }

        return result;
    }

    @Override
    protected void beforeAsyncCall() {
        super.beforeAsyncCall();
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(int actionIndex) throws Exception {
        this.actionIndex = actionIndex;
        String response = getRemoteJson(actionIndex);
        serviceListModel = new ServicesListModel();
        serviceListModel.setModel(new JSONObject(response));
        return response;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        super.afterAsyncCall(result);

        if(serviceListModel.isSuccessful) {
            if(actionIndex == 0)
                showServices();
            else
                onPostServicesRequest();
        }
        else {
            showErrorMessage(serviceListModel.responseMessage, getActivity().getString(R.string.error));
        }

        clickedViewId = 0;
    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {
        toggleSubContent(view);
        setSelectedService(view.getId());
    }

    public void onPostServicesRequest() {
        showSuccessMessage(serviceListModel.responseMessage, getActivity().getString(R.string.success));
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