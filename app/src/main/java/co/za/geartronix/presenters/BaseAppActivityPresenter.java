package co.za.geartronix.presenters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.customViews.CustomImageVIew;
import co.za.geartronix.providers.Permissions;
import co.za.geartronix.providers.ResizeAnimation;

public abstract class BaseAppActivityPresenter extends BaseAsyncPresenter {

    protected int AppActivityIndex;
    protected int icon;
    protected int activeColor, defColor;
    protected int inactivecolor;
    protected String activityName, displayName;
    protected BaseAppActivityPresenter currentAppActivity;
    protected FrameLayout controlMenu, largeContainerFrm;
    public boolean imageEnlarged;
    protected CustomImageVIew activeImage;
    protected long imageAnimationDuration;
    protected String imageName;
    protected Uri imageFilePath;
    private int ogWidth, ogHeight;
    private View lastSubView, lastViewSideIcon;
    private boolean openState;
    protected MenuItem requestMenuItem;

    public BaseAppActivityPresenter(BaseActivity baseActivity, int index) {
        setProperties(baseActivity, index);
    }

    public BaseAppActivityPresenter(BaseActivity baseActivity) {
        activity = baseActivity;
        imageAnimationDuration = 400;
    }

    public void setProperties(BaseActivity baseActivity, int index) {
       activity = baseActivity;
       setActivityName( this.getClass().toString() );
       setCurrentAppActivity(this);
       setActiveColor(Color.parseColor("#0088cc"));
       setInactivecolor(activity.getResources().getColor(R.color.tileTransBlack));
    }

    public String getFormatedName(String name) {
       String formatedName = name.split("Presenter")[0];
       return formatedName;
    }

    public void goToCurrentAppActivity() {
        showShortToast(displayName);
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
    }

    public int getInactivecolor() {
        return inactivecolor;
    }

    public void setInactivecolor(int inactivecolor) {
        this.inactivecolor = inactivecolor;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BaseAppActivityPresenter getCurrentAppActivity() {
        return currentAppActivity;
    }

    public void setCurrentAppActivity(BaseAppActivityPresenter currentAppActivity) {
        this.currentAppActivity = currentAppActivity;
    }



    private String getImageName() {
// Todo: fix date time
        return "image"+Math.random(); //new DateTimeProvider().getFormatedDateAndTime();
    }

    private void setImageName() {
        imageName = getImageName();
    }

    protected void showPanels(View view) {
        ImageView selectedImage = (ImageView) view;
        BitmapDrawable bd = (BitmapDrawable)selectedImage.getDrawable();
        Bitmap image = bd.getBitmap();
        activeImage.setImageBitmap(image);

// Todo: fix
        Object imageTag = selectedImage.getTag();
        if(imageTag != null)
            imageFilePath = Uri.parse(imageTag.toString());

        setImageName();

        imageEnlarged = true;

        largeContainerFrm.setVisibility(View.VISIBLE);
        largeContainerFrm.animate().alpha(1.0f).setDuration(imageAnimationDuration);
    }

    protected void hidePanels() {
        imageEnlarged = false;

        largeContainerFrm.animate()
                .alpha(0.0f)
                .setDuration(imageAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(imageEnlarged)
                            return;

                        largeContainerFrm.setVisibility(View.GONE);
                    }
                });
    }

    protected void setLargeImageViews() {
        largeContainerFrm = (FrameLayout)activity.findViewById(R.id.imgLargeContainer);
        largeContainerFrm.animate().alpha(0.0f);
        activeImage = largeContainerFrm.findViewById(R.id.imgLarge);
        controlMenu = largeContainerFrm.findViewById(R.id.frmContrlMenu);
    }


    protected void showEnlargedImage(View view) {
        showPanels(view);
    }

    public void hideEnlargedImage() {
        hidePanels();
    }


    protected void handleEnlargedImageMethods(View view) {
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

    protected void shareImage(View view) {

        Uri fileUri = Uri.parse("android.resource://com.cpt.sample/raw/test_pic.png");

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        shareIntent.setType("image/png");
        activity.startActivity(Intent.createChooser(shareIntent, activity.getString(R.string.send_to)));

        /*
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageFilePath);
        shareIntent.setType("image/png");
        getActivity().startActivity(Intent.createChooser(shareIntent, getActivity().getString(R.string.send_to)));
        */
    }

    protected void saveCurrentImageToGallery() {
        String permission = Permissions.writeStorage.getPermission();
        ImageView iv = activeImage;
        iv.buildDrawingCache();
        Bitmap bmp = iv.getDrawingCache();

        //context.getExternalFilesDir(null);
        File storageLoc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File file = new File(storageLoc, imageName + ".png");

        try{
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            scanFile(activity, Uri.fromFile(file));
            showShortToast(activity.getString(R.string.picture_saved_to_gallery));

        } catch (FileNotFoundException e) {
            requestPermission(permission);

        } catch (IOException e) {
            requestPermission(permission);
        }
    }

    private void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        context.sendBroadcast(scanIntent);
    }


    public void fadeInOverlay(View view) {
        view.animate().alpha(1.0f).setDuration(imageAnimationDuration);
    }

    protected void fadeOutOverlay(View view) {
        view.animate().alpha(0.0f).setDuration(imageAnimationDuration);
        /*
        final View ofverlay = view;

        ofverlay.animate()
                .alpha(0.0f)
                .setDuration(imageAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ofverlay.setVisibility(View.GONE);
                    }
                });
        */
    }

    protected void openedStateMethod() {

    }

    protected void closedStateMethod() {
    }

    public void toggleSubContent(View view) {

        LinearLayout parentLayout = (LinearLayout)view;
        FrameLayout sideIconImgParent = (FrameLayout)parentLayout.getChildAt(0);
        View sideIconImg = sideIconImgParent.getChildAt(0);

        if(ogWidth == 0) {
            ogWidth = sideIconImg.getWidth();
            ogHeight = sideIconImg.getHeight();
        }

        if(lastViewSideIcon != null && lastViewSideIcon != sideIconImg) {
            ViewGroup.LayoutParams lpOg = lastViewSideIcon.getLayoutParams();
            lpOg.width = ogWidth;
            lpOg.height = ogHeight;
            lastViewSideIcon.setLayoutParams(lpOg);
        }

        if(lastViewSideIcon == sideIconImg && openState){
            ViewGroup.LayoutParams lpOg = sideIconImg.getLayoutParams();
            lpOg.width = ogWidth;
            lpOg.height = ogHeight;
            sideIconImg.setLayoutParams(lpOg);
            openedStateMethod();
        }
        else {
            ViewGroup.LayoutParams lp = sideIconImg.getLayoutParams();
            lp.width = ogWidth + 20;
            lp.height = ogHeight + 20;
            sideIconImg.setLayoutParams(lp);
            closedStateMethod();
        }

        lastViewSideIcon = sideIconImg;

        LinearLayout rightContainer = (LinearLayout)parentLayout.getChildAt(1);
        View subView = rightContainer.getChildAt(1);

        int activeColor = ((ColorDrawable) sideIconImgParent.getBackground()).getColor();

        if(defColor == 0)
            defColor = ((ColorDrawable) parentLayout.getBackground()).getColor();

        resetLastAndSetNew(view, defColor, activeColor);

        if(lastSubView != null && lastSubView != subView)
            slideDraws(lastSubView, 0, 0);

        if(lastSubView == subView && openState)
            slideDraws(subView, 0, 0);
        else
            slideDraws(subView, LinearLayout.LayoutParams.WRAP_CONTENT, 0);

        lastSubView = subView;

        openState = !openState;
    }

    protected void slideDraws(View view, int target, int start) {
        ResizeAnimation resizeAnimation = new ResizeAnimation(view, target, start);
        resizeAnimation.setDuration(0);
        view.startAnimation(resizeAnimation);
    }

}