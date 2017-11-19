package com.emgr.geartronix.presenters;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.emgr.geartronix.R;
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.activities.GalleryActivity;
import com.emgr.geartronix.adapters.GalleryImageAdapter;
import com.emgr.geartronix.views.IGalleryView;

import java.util.ArrayList;
import java.util.List;


public class GalleryPresenter extends BaseAsyncPresenter implements IGalleryPresenter {

    private GridView imageGridlayout;
    private View gridItemView;
    private List<ArrayList> items;
    private int columnCount;
    private int viewType;

    public GalleryPresenter(IGalleryView iGalleryView) {
        setDependanciesChildActivities((BaseActivity) iGalleryView, R.layout.activity_gallery);
        setPageTitle(getActivity().getString(R.string.Gallery));
        setViews();

        new DoAsyncCall(null).execute();
    }

    private List<ArrayList> getItems() {
        items = new ArrayList<>();

        ArrayList item1 = new ArrayList();
        item1.add("GearBox");
        item1.add("This is the description for this gearbox");
        item1.add(R.drawable.gearbox);

        ArrayList item2 = new ArrayList();
        item2.add("GearBox 2");
        item2.add("This is the description 2 for this gearbox");
        item2.add(R.drawable.gearbox);

        ArrayList item3 = new ArrayList();
        item3.add("GearBox 3");
        item3.add("3 This is the description 2 for this gearbox");
        item3.add(R.drawable.bg_landscape);

        ArrayList item4 = new ArrayList();
        item4.add("GearBox 4");
        item4.add("4 This is the 4 This is the4 This is the4 This is the4 This is the4 This is the4 This is the4 This is the4 This is the4 This is the4 This is the4 This is the4 This is the 4 This is the4 This is the");
        item4.add(R.mipmap.ic_launcher_round);

        ArrayList item5 = new ArrayList();
        item5.add("GearBox 5");
        item5.add("5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555");
        item5.add(R.mipmap.ic_launcher_round);

        ArrayList item6 = new ArrayList();
        item6.add("GearBox 6");
        item6.add("6");
        item6.add(R.mipmap.ic_launcher_round);

        ArrayList item7 = new ArrayList();
        item7.add("GearBox 7");
        item7.add("");
        item7.add(R.mipmap.ic_launcher_round);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);

        return items;
    }

    private ArrayAdapter setGridItems(int layout) {
        imageGridlayout = (GridView)activity.findViewById(R.id.imageGrid);
        return new GalleryImageAdapter(activity, imageGridlayout, layout, getItems(), getColumnWidth());
    }

    private void setImageGridItems(ArrayAdapter adapter) {
        imageGridlayout.setAdapter(adapter);
    }

    public void setInline() {
        if(imageGridlayout.getNumColumns() == 1)
            return;

        viewType = 1;
        new DoAsyncCall(null).execute();
    }

    public void setGrid() {
        if(imageGridlayout != null && imageGridlayout.getNumColumns() == GridView.AUTO_FIT)
            return;

        viewType = 0;
        new DoAsyncCall(null).execute();
    }

    private void setInlineView() {
        imageGridlayout.setNumColumns(1);
        imageGridlayout.setColumnWidth((int)getScreenWidth());
    }

    private void setGridView() {
        imageGridlayout.setNumColumns(getColumnCount());
        //imageGridlayout.setNumColumns(GridView.AUTO_FIT);
        imageGridlayout.setColumnWidth((int)getColumnWidth());
    }

    public float[] getScreenWidthAndHeight() {

        float width = 0;
        float height = 0;

        width =  activity.getWindowManager().getDefaultDisplay().getWidth();
        height = activity.getWindowManager().getDefaultDisplay().getHeight();

        return new float[]{width, height};
    }

    public float getScreenWidth() { return getScreenWidthAndHeight()[0];}
    public float getScreenHeight() {return getScreenWidthAndHeight()[1];}

    public int getColumnCount() {

        float screenWidth = getScreenWidth();
        columnCount = 4;

        if(screenWidth > 2000)
            columnCount = 6;

        return columnCount;
    }

    public float getColumnWidth() {
        return getScreenWidth() / getColumnCount();
    }

    private FrameLayout currentItem;
    private FrameLayout lastItem;
    private RelativeLayout currentInfoView;

    public void animateClickedView(View view) {

        //Animation anim = AnimationUtils.loadAnimation(context, R.anim.item_anim);
        //anim.setStartOffset(1 * 200);

        //view.setAnimation(anim);
        //anim.start();

        if(lastItem == null) currentItem = (FrameLayout)view;

        if(view == lastItem)
            return;

        resetCurrentItem();

        currentItem = (FrameLayout)view;
        setCurrentItem();

        lastItem = currentItem;
    }

    public void openDetailedView(View view) {
        RelativeLayout mee = (RelativeLayout)view;
        ImageView currentImage = (ImageView) currentItem.getChildAt(0);

        ImageView largeImage = (ImageView)activity.findViewById(R.id.imgLrg);
        largeImage.setImageBitmap(getImageViewPic(currentImage));
    }

    public void closeDetailedView(View view) {
        RelativeLayout detailedView = (RelativeLayout)activity.findViewById(R.id.detailedView);
        detailedView.setVisibility(View.GONE);
    }

    private void resetCurrentItem() {
        setCurrentItemStatus(View.INVISIBLE, R.color.colorTransparent);
    }

    private void setCurrentItem() {
        setCurrentItemStatus(View.VISIBLE, R.color.blue_one);
    }

    private void setCurrentItemStatus(int visibility, int borderColor) {
        setCurrentSelectedColor(activity.getResources().getColor(borderColor));
        currentInfoView = (RelativeLayout) currentItem.getChildAt(1);
        currentInfoView.setVisibility(visibility);
    }

    private void setCurrentSelectedColor(int color) {
        currentItem.setBackgroundColor(color);
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

        int gridViewParams;

        switch (viewType)
        {
            case 0:
                gridViewParams = R.layout.image_gallery_view_for_grid;
            break;
            case 1:
                gridViewParams = R.layout.image_gallery_view_for_inline;
            break;
            default:
                gridViewParams = R.layout.image_gallery_view_for_grid;
            break;
        }

        return setGridItems(gridViewParams);
    }

    @Override
    protected void afterAsyncCall(Object result) {

        ArrayAdapter imgGridAdapter = (ArrayAdapter)result;

        switch (viewType)
        {
            case 0:
                setGridView();
            break;
            case 1:
                setInlineView();
            break;
        }

        setImageGridItems(imgGridAdapter);
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

        switch (view.getId())
        {
            case R.id.imgCloseDetailedView:
                closeDetailedView(view);
            break;
            case R.id.imgBtnInlineLayout:
                setInline();
            break;
            case R.id.imgBtnBlockLayout:
                setGrid();
            break;
            case R.id.rltyImageContainer:
                openDetailedView(view);
            break;
        }
    }
}
