package co.za.geartronix.presenters;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.GalleryActivity;
import co.za.geartronix.adapters.GalleryImageAdapter;
import co.za.geartronix.models.BaseModel;
import co.za.geartronix.models.GalleryModel;
import co.za.geartronix.models.ImageModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.views.IGalleryView;
import org.json.JSONException;
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
        setLoadingText("Loading services...");

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
    protected boolean hasUpdate(BaseModel baseModel) {
        GalleryModel remoteGalleryModel = (GalleryModel)baseModel;
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
    public String makeGalleryHttpCall() throws IOException {
        String service = DataServiceProvider.gallery.getPath();
        String url = environment + service;

        return new HttpConnectionProvider().makeCallForData(url, "GET", true, true, httpConTimeout);
    }

    @Override
    protected String getRemoteJson(int methodIndex) throws IOException {
        if(methodIndex == 0)
            return makeGalleryHttpCall();

        return null;
    }

    @Override
    public String checkServicesUpdate() throws IOException, JSONException {
        GalleryModel remoteGalleryModel = new GalleryModel();
        String response = getRemoteJson(actionIndex);
        remoteGalleryModel.setModel(new JSONObject(response));

        if (hasUpdate(remoteGalleryModel) || !isCached())
            cacheProvider.updateGallery(remoteGalleryModel);

        return response;
    }

    @Override
    public String getGallery() throws IOException, JSONException {
        galleryModel = cacheProvider.getGalleryImages();
        String response = "";

        if(!isCached()) {
            galleryModel = new GalleryModel();
            response = getRemoteJson(actionIndex);
            galleryModel.setModel(new JSONObject(response));
            cacheProvider.updateGallery(galleryModel);
        }

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

        return getGallery();
    }

    @Override
    protected void afterAsyncCall(Object result) {
        if(outOfFocus)
            return;

        if(isCheckingUpdates) {
            isCheckingUpdates = false;
            return;
        }

        if(galleryModel.isSuccessful) {

            if(isCached())
                checkAndUpdate();

            showGallery();
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
        laodingTxt = (TextView) getActivity().findViewById(R.id.txtLoading);
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
    public void showGallery() {
        imagesGridview = (GridView) activity.findViewById(R.id.grdvImageContainer);
        ArrayAdapter adp = new GalleryImageAdapter(activity, R.layout.gallery_item_view, galleryModel.getImages());
        imagesGridview.setAdapter(adp);
    }

    @Override
    public void goToCurrentAppActivity() {
        goToGallery();
    }

}