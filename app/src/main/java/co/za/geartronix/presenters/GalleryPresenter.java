package co.za.geartronix.presenters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.GalleryActivity;
import co.za.geartronix.adapters.GalleryImageAdapter;
import co.za.geartronix.models.GalleryModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.IGalleryView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class GalleryPresenter extends BaseAppActivityPresenter implements IGalleryPresenter {

    private GridView imagesGridview;
    private List<ArrayList> items;
    private GalleryModel responseModel;

    public GalleryPresenter(IGalleryView iGalleryView) {
        super((BaseActivity)iGalleryView);
        setDependanciesChildActivities(R.layout.activity_gallery);
        currentActionBar.setTitle(" "+activity.getString(R.string.gallery));
        setViews();
        responseModel = new GalleryModel();
        new DoAsyncCall().execute();
    }

    public GalleryPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.gallery_icon);
        setDisplayName(activity.getString(R.string.gallery));
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
        if(outOfFocus)
            return;

        try {
            String res = result.toString();
            responseModel.setModel(new JSONObject(res));
            porpulateServiceList();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        hideLoadingScreen();
    }

    @Override
    protected void handleAsyncButtonClickedEvent(View view) {

        int viewId = view.getId();

        if(viewId == R.id.imgPic)
            showEnlargedImage(view);
        else
            blinkView(view, 60, 140);
    }

    @Override
    public void fullScreeView() {
        showShortToast("Full screen mode");
        //imagesGridview
    }

    @Override
    public void setViews() {
        setAsyncViews();
        setLargeImageViews();
    }

    @Override
    public GalleryActivity getActivity() {
        return (GalleryActivity) activity;
    }

    @Override
    public void handleViewClickedEvent(View view) {
        handleAsyncButtonClickedEvent(view);
    }


    @Override
    protected void duringAnimation(View view) {

    }

    @Override
    protected void postAnimation(View view) {
        //int viewId = view.getId();
        handleEnlargedImageMethods(view);
    }

    public void menuOptionSelected(MenuItem item) {
        super.menuOptionSelected(item);

        clickedViewId = item.getItemId();

        if(clickedViewId == R.id.action_fullscreen)
            fullScreeView();
    }

    @Override
    public void porpulateServiceList() {
        items = responseModel.getItems();
        ArrayAdapter adp = new GalleryImageAdapter(activity, R.layout.gallery_item_view, items);
        imagesGridview = (GridView) activity.findViewById(R.id.grdvImageContainer);
        imagesGridview.setAdapter(adp);
    }

    @Override
    public void goToCurrentAppActivity() {
        goToGallery();
    }

}