package co.za.geartronix.presenters;

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
    public void handleViewClickedEvent(View view) {

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
    public void requestService() {

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

    public void menuOptionSelected(MenuItem item) {
        clickedViewId = item.getItemId();
        showShortToast("menu option");
    }
}
