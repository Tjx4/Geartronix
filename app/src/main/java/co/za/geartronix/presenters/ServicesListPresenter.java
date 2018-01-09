package co.za.geartronix.presenters;

import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.ServicesListActivity;
import co.za.geartronix.adapters.ServicesAdapter;
import co.za.geartronix.models.ServiceModel;
import co.za.geartronix.models.ServicesListModel;
import co.za.geartronix.providers.MockProvider;
import co.za.geartronix.providers.ResizeAnimation;
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

    private int ogsize;

    @Override
    protected void postAnimation(View view) {
        //RotateAnimation animation = new RotateAnimation(90, 20);
        //view.startAnimation(animation);
        //view.animate().rotationBy(360).setDuration(600).setInterpolator(new LinearInterpolator()).start();

       /*

    if(ogsize == 0)
            ogsize = view.getWidth();

        if(lastViewArrow != null && lastViewArrow != view) {
            ViewGroup.LayoutParams lp = lastViewArrow.getLayoutParams();
            lp.width = ogsize;
            lastViewArrow.setLayoutParams(lp);
        }

        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = ogsize + 10;
        view.setLayoutParams(lp);

        lastViewArrow = view;

        */
    }

    private View lastTxtView, lastViewArrow;

    @Override
    public void handleViewClickedEvent(View view) {

        LinearLayout parentLayout = (LinearLayout)view;
        FrameLayout arrowImgParent = (FrameLayout)parentLayout.getChildAt(0);
        ImageView arrowImg = (ImageView)arrowImgParent.getChildAt(0);

        //blinkView(arrowImg, 30, 70);

        if(ogsize == 0)
            ogsize = arrowImg.getWidth();

        if(lastViewArrow != null && lastViewArrow != arrowImg) {
            ViewGroup.LayoutParams lpOg = lastViewArrow.getLayoutParams();
            lpOg.width = ogsize;
            lastViewArrow.setLayoutParams(lpOg);
        }

        ViewGroup.LayoutParams lp = arrowImg.getLayoutParams();
        lp.width = ogsize + 24;
        arrowImg.setLayoutParams(lp);

        lastViewArrow = arrowImg;

        resetLastAndSetNew(view, Color.TRANSPARENT, getActivity().getResources().getColor(R.color.activeService));

        LinearLayout discriptionTxtContainer = (LinearLayout)parentLayout.getChildAt(1);
        TextView discriptionTxt = (TextView)discriptionTxtContainer.getChildAt(1);

        if(lastTxtView != null && lastTxtView != discriptionTxt)
            slideDraws(lastTxtView, 0, 0);

        slideDraws(discriptionTxt, FrameLayout.LayoutParams.WRAP_CONTENT, 0);

        lastTxtView = discriptionTxt;

        showShortToast("service..."+view.getId());
    }

    protected void slideDraws(View view, int target, int start) {
        ResizeAnimation resizeAnimation = new ResizeAnimation(view, target, start);
        resizeAnimation.setDuration(0);
        view.startAnimation(resizeAnimation);
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
