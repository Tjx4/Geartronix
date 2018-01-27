package co.za.geartronix.presenters;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.GalleryActivity;
import co.za.geartronix.adapters.GalleryImageAdapter;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.models.GalleryModel;
import co.za.geartronix.models.ImageModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.IGalleryView;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;

public class GalleryPresenter extends BaseOverflowMenuPresenter implements IGalleryPresenter {

    private GridView imagesGridview;
    private GalleryModel galleryModel;

    public GalleryPresenter(IGalleryView iGalleryView) {
        super((BaseActivity)iGalleryView);
        setDependanciesChildActivities(R.layout.activity_gallery);
        currentActionBar.setTitle(" "+activity.getString(R.string.gallery));
        setViews();

        new DoAsyncCall().execute(0);
        permissionProvider.requestWriteStoragePermission();
    }

    public GalleryPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
        setIcon(R.mipmap.gallery_icon);
        setDisplayName(activity.getString(R.string.gallery));
    }

    @Override
    protected void checkAndUpdate() {
        isCheckingUpdates = true;
        new DoAsyncCall().execute(0);
    }

    @Override
    protected boolean isCached() {
        return galleryModel != null && !galleryModel.getImages().isEmpty();
    }

    @Override
    protected boolean hasUpdate(GalleryModel remoteGalleryModel) {
        List<ImageModel> currImages = galleryModel.getImages();
        List<ImageModel> remoteImages = remoteGalleryModel.getImages();
//TODO: Fix
        boolean isSame = false;
        int indx = 0;
        for(ImageModel i : currImages){

            if( !i.getDescription().equals( remoteImages.get(indx).getDescription() ) )
                isSame = true;

            indx++;
        }

        //!currImages.equals(remoteImages);
        return isSame;
    }

    @Override
    public String getGallery() throws IOException {
        String service = DataServiceProvider.gallery.getPath();
        String url = environment + service;

        return new HttpConnectionProvider().makeCallForData(url, "GET", true, true, httpConTimeout);
    }

    @Override
    protected String getRemoteJson(int methodIndex) throws IOException {
        if(methodIndex == 0)
            return getGallery();

        return null;
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

try {
    galleryModel = cacheProvider.getGalleryImages();

}
catch (Exception e) {

}
        String response = "";

        if(isCheckingUpdates) {
            GalleryModel remoteGalleryModel = new GalleryModel();
            response = getRemoteJson(actionIndex);
            remoteGalleryModel.setModel(new JSONObject(response));

            if (hasUpdate(remoteGalleryModel) || !isCached())
                cacheProvider.updateGallery(remoteGalleryModel);
        }
        else {
            if(!isCached()) {
                galleryModel = new GalleryModel();
                response = getRemoteJson(actionIndex);
                galleryModel.setModel(new JSONObject(response));
                cacheProvider.updateGallery(galleryModel);
            }
        }

        return response;
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        if(isNullModel(galleryModel)) {
            getActivity().finish();
            Bundle extras = new Bundle();
            extras.putString(Constants.FEATURE_ERROR, getActivity().getString(R.string.feature_error_message));
            goToDashboard(extras);
            return;
        }

        if(galleryModel.isSuccessful) {

            if(isCheckingUpdates) {
                isCheckingUpdates = false;
                return;
            }

            if(isCached())
                checkAndUpdate();

            porpulateServiceList();

        }
        else {
            showErrorMessage(galleryModel.getResponseMessage(), getActivity().getString(R.string.error));
        }
        
        super.afterAsyncCall(result);
    }

    @Override
    public void handleAsyncButtonClickedEvent(View view) {

        int viewId = view.getId();

        if(viewId == R.id.imgPic)
            showEnlargedImage(view);
        else
            blinkView(view, 60, 140);
    }

    @Override
    public void fullScreeView() {
        showShortToast("Full screen mode");
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
        imagesGridview = (GridView) activity.findViewById(R.id.grdvImageContainer);
        ArrayAdapter adp = new GalleryImageAdapter(activity, R.layout.gallery_item_view, galleryModel.getImages());
        imagesGridview.setAdapter(adp);
    }

    @Override
    public void goToCurrentAppActivity() {
        goToGallery();
    }

}