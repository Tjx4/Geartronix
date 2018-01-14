package co.za.geartronix.presenters;

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
    protected void postAnimation(View view) {
        //RotateAnimation animation = new RotateAnimation(90, 20);
        //view.startAnimation(animation);
        //view.animate().rotationBy(360).setDuration(600).setInterpolator(new LinearInterpolator()).start();

       /*

        if(ogWidth == 0)
            ogWidth = view.getWidth();

        if(lastViewSideIcon != null && lastViewSideIcon != view) {
            ViewGroup.LayoutParams lp = lastViewSideIcon.getLayoutParams();
            lp.width = ogWidth;
            lastViewSideIcon.setLayoutParams(lp);
        }

        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = ogWidth + 10;
        view.setLayoutParams(lp);

        lastViewSideIcon = view;

        */
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
        //blinkView(sideIconImg, 30, 70);
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
    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);

        clickedViewId = item.getItemId();

        if(clickedViewId == R.id.action_request_service) {
            showShortToast("You are requesting"+selectedService.getService());
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