package com.emgr.geartronix.presenters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

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
    private ImageView activeImage;
    private FrameLayout controlMenu;
    public boolean enlarged;

    public GalleryPresenter(IGalleryView iGalleryView) {
        setDependanciesChildActivities((BaseActivity) iGalleryView, R.layout.activity_gallery);
        currentActionBar.setTitle(" "+activity.getString(R.string.Gallery));
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
    protected void handleAsyncButtonClickedEvent(View view) {

        int viewId = view.getId();

        switch (viewId)
        {
            case R.id.imgPic :
                showEnlargedImage(view);
            break;
            case R.id.imgMinimize :
                hideEnlargedImage();
            break;
            case R.id.imgBtnShare :
                showShortToast("Call share");
            break;
            case R.id.imgBtnSavePic :
                showShortToast("Save picture");
            break;
        }
    }

    @Override
    public void showEnlargedImage(View view) {
        ImageView selectedImage = (ImageView) view;
        BitmapDrawable bd = (BitmapDrawable)selectedImage.getDrawable();
        Bitmap image = bd.getBitmap();

        activeImage.setImageBitmap(image);
        showPanels();
    }

    @Override
    public void hideEnlargedImage() {
        //ImageView selectedImage = (ImageView) view;
        //BitmapDrawable bd = (BitmapDrawable)selectedImage.getDrawable();
        //Bitmap image = bd.getBitmap();
        //activeImage.setImageBitmap(image);

        hidePanels();
    }

    private void showPanels() {
        enlarged = true;
        controlMenu.setVisibility(View.VISIBLE);
        activeImage.setVisibility(View.VISIBLE);
    }

    private void hidePanels() {
        controlMenu.setVisibility(View.GONE);
        activeImage.setVisibility(View.GONE);
        enlarged = false;
    }

    @Override
    public void setViews() {
        setAsyncViews();
        activeImage = (ImageView)getActivity().findViewById(R.id.imgLarge);
        controlMenu = (FrameLayout)getActivity().findViewById(R.id.frmContrlMenu);
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
    public void openDetailedView(View view) {

    }

    @Override
    public void closeDetailedView(View view) {

    }

    @Override
    public void porpulateGrid() {

    }
}