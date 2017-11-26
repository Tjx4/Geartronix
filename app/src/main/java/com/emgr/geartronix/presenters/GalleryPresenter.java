package com.emgr.geartronix.presenters;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.GalleryActivity;
import com.emgr.geartronix.adapters.GalleryImageAdapter;
import com.emgr.geartronix.models.GalleryModel;
import com.emgr.geartronix.providers.DataServiceProvider;
import com.emgr.geartronix.providers.HttpConnectionProvider;
import com.emgr.geartronix.views.IGalleryView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GalleryPresenter extends BaseAsyncPresenter implements IGalleryPresenter {

    private GridView images;
    private List<ArrayList> items;
    private GalleryModel responseModel;

    public GalleryPresenter(IGalleryView iGalleryView) {
        setDependanciesChildActivities((BaseActivity) iGalleryView, R.layout.activity_gallery);
        setPageTitle(activity.getString(R.string.Gallery));
        setViews();
        responseModel = new GalleryModel();
        new DoAsyncCall().execute();
    }

    @Override
    protected void beforeAsyncCall() {
        showLoadingScreen();
    }

    @Override
    protected void duringAsyncCall(Integer... values) {

    }

    @Override
    protected Object doAsyncOperation(Object... args) throws Exception {
        String service = DataServiceProvider.gallery.getPath();
        String url = environment + service + "index.php";
        return new HttpConnectionProvider().makeCallForData(url, "GET", true, true, httpConTimeout);
    }

    @Override
    protected void afterAsyncCall(Object result) {
        String res = result.toString();
        try {

        responseModel.setModel(new JSONObject(res));

        items = responseModel.getItems();
        ArrayAdapter adp = new GalleryImageAdapter(activity, R.layout.gallery_item_view, items);
        images = (GridView) activity.findViewById(R.id.grdvImageContainer);
        images.setAdapter(adp);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        hideLoadingScreen();
    }

    @Override
    protected void handleAsyncButtonClickedEvent(View button) {

    }

    @Override
    public void setViews() {
        setAsyncViews();
    }

    @Override
    public GalleryActivity getActivity() {
        return (GalleryActivity) activity;
    }

    @Override
    public void handleButtonClickedEvent(View view) {

    }

    @Override
    public void openDetailedView(View view) {

    }

    @Override
    public void closeDetailedView(View view) {

    }

    @Override
    public void porpulateGrid() {

    }
}