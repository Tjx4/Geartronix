package co.za.geartronix.presenters;

import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ServicesListActivity;
import co.za.geartronix.adapters.ServicesAdapter;
import co.za.geartronix.models.ServiceModel;
import co.za.geartronix.models.ServicesListModel;
import co.za.geartronix.providers.MockProvider;
import co.za.geartronix.views.IServicesListView;

public class ServicesListPresenter extends BaseAppActivityPresenter implements IServicesListPresenter {

    private ServicesListModel responseModel;
    private List<ServiceModel> services;
    private ServiceModel selectedService;
    private ListView servicesLst;

    public ServicesListPresenter(IServicesListView iGalleryView) {
        super((BaseActivity)iGalleryView);
        setDependanciesChildActivities(R.layout.activity_services_list);
        currentActionBar.setTitle(" "+activity.getString(R.string.services));
        setViews();
        responseModel = new ServicesListModel();
        new BaseAsyncPresenter.DoAsyncCall().execute();
    }

    public ServicesListPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.service_icon);
        setDisplayName(activity.getString(R.string.services));
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
    public void handleViewClickedEvent(View view) {
        toggleSubContent(view);
        setSelectedService(view.getId());
    }

    @Override
    public void setViews() {
        servicesLst = (ListView)getActivity().findViewById(R.id.lstServices);
        showServices();
    }

    @Override
    public ServicesListActivity getActivity() {
        return (ServicesListActivity)activity;
    }

    @Override
    public void showServices() {
        services = new MockProvider(getActivity()).getMockServiceList();
        ArrayAdapter servicesAdapter = new ServicesAdapter(getActivity(), R.layout.service_item, services);
        servicesLst.setAdapter(servicesAdapter);
    }

    @Override
    public void setSelectedService(int id) {
        selectedService = services.get(id);
    }

    @Override
    public void requestService() {
        showShortToast("You are requesting"+selectedService.getService());
    }

    @Override
    public void generalCheckUpRequest() {
        // Send email to geartronix with user specifics
        showShortToast("generalCheckUpRequest");
    }

    @Override
    protected void beforeAsyncCall() {

    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
        return null;
    }

    @Override
    protected void afterAsyncCall(Object result) {

    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    protected void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i) {

        if(clickedViewId == R.id.action_request_service)
            requestService();
        else
        if(clickedViewId == R.id.action_general_checkup)
            generalCheckUpRequest();
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