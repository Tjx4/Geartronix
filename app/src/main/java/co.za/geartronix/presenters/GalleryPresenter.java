package co.za.geartronix.presenters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.activities.GalleryActivity;
import co.za.geartronix.adapters.GalleryImageAdapter;
import co.za.geartronix.customViews.CustomImageVIew;
import co.za.geartronix.models.GalleryModel;
import co.za.geartronix.providers.DataServiceProvider;
import co.za.geartronix.providers.HttpConnectionProvider;
import co.za.geartronix.providers.Permissions;
import co.za.geartronix.views.IGalleryView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class GalleryPresenter extends BaseAppActivityPresenter implements IGalleryPresenter {

    private GridView images;
    private List<ArrayList> items;
    private GalleryModel responseModel;
    private CustomImageVIew activeImage;
    private FrameLayout controlMenu;
    public boolean enlarged;
    private FrameLayout activeImageContainer;
    private String imageName;
    private Uri imageFilePath;

    public GalleryPresenter(IGalleryView iGalleryView) {
        super((BaseActivity)iGalleryView);
        setDependanciesChildActivities(R.layout.activity_gallery);
        currentActionBar.setTitle(" "+activity.getString(R.string.gallery));
        setViews();
        responseModel = new GalleryModel();
        new DoAsyncCall().execute();
    }

    private void setImageName() {
        imageName = getImageName();
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
        String res = result.toString();
        try {
            responseModel.setModel(new JSONObject(res));
            porpulateImageGrid();

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
            blinkView(view);
    }

    @Override
    public void showEnlargedImage(View view) {
        ImageView selectedImage = (ImageView) view;
        BitmapDrawable bd = (BitmapDrawable)selectedImage.getDrawable();
        Bitmap image = bd.getBitmap();
        activeImage.setImageBitmap(image);
        //activeImage.setTag(selectedImage.getTag().toString());
        imageFilePath = Uri.parse(selectedImage.getTag().toString());
        showPanels();
    }

    @Override
    public void hideEnlargedImage() {
        hidePanels();
    }

    private void showPanels() {
        setImageName();
        enlarged = true;
        controlMenu.setVisibility(View.VISIBLE);
        activeImageContainer.setVisibility(View.VISIBLE);
    }

    private void hidePanels() {
        controlMenu.setVisibility(View.GONE);
        activeImageContainer.setVisibility(View.GONE);
        enlarged = false;
    }

    @Override
    public void setViews() {
        setAsyncViews();
        activeImage = (CustomImageVIew)getActivity().findViewById(R.id.imgLarge);
        activeImage.setOnTouchListener(new CustomImageVIew.OnTouchListener() {
//Todo: quick hack to enable pinch
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                CustomImageVIew mee = (CustomImageVIew)v;
                mee.setScaleType(ImageView.ScaleType.MATRIX);
                return false;
            }
        });
        activeImageContainer = (FrameLayout)getActivity().findViewById(R.id.imgLargeContainer);
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
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings( "deprecation" )
    public void shareImage(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageFilePath);
        shareIntent.setType("image/jpg");
        getActivity().startActivity(Intent.createChooser(shareIntent, "Send to"));
    }

    @Override
    protected void duringAnimation(View view) {

    }

    @Override
    protected void postAnimation(View view) {

        int viewId = view.getId();

        switch (viewId)
        {
            case R.id.imgMinimize :
                hideEnlargedImage();
                break;
            case R.id.imgBtnShare :
                shareImage(view);
                break;
            case R.id.imgBtnSavePic :
                saveCurrentImageToGallery();
                break;
        }
    }

    private void saveCurrentImageToGallery() {
        String permission = Permissions.writeStorage.getPermission();
        ImageView iv = activeImage;
        iv.buildDrawingCache();
        Bitmap bmp = iv.getDrawingCache();

        //context.getExternalFilesDir(null);
        File storageLoc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File file = new File(storageLoc, imageName + ".jpg");

        try{
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            scanFile(getActivity(), Uri.fromFile(file));
            showShortToast(getActivity().getString(R.string.picture_saved_to_gallery));

        } catch (FileNotFoundException e) {
            requestPermission(permission);

        } catch (IOException e) {
            requestPermission(permission);
        }
    }

    @Override
    public String getImageName() {
        // Todo: fix date time
        return "image"+Math.random(); //new DateTimeProvider().getFormatedDateAndTime();
    }

    private void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        context.sendBroadcast(scanIntent);
    }

    @Override
    public void porpulateImageGrid() {
        items = responseModel.getItems();
        ArrayAdapter adp = new GalleryImageAdapter(activity, R.layout.gallery_item_view, items);
        images = (GridView) activity.findViewById(R.id.grdvImageContainer);
        images.setAdapter(adp);
    }

    @Override
    public void goToCurrentAppActivity() {
        goToGallery();
    }
}