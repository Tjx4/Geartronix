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
import com.emgr.geartronix.adapters.GalleryImageAdapter;
import com.emgr.geartronix.views.IGalleryView;

import java.util.ArrayList;
import java.util.List;


public class GallaryPresenter extends BaseAsyncPresenter implements IGallaryPresenter {

    private GridView imageGridlayout;
    private View gridItemView;
    private List<ArrayList> items;
    private int columnCount;

    public GallaryPresenter(IGalleryView iGalleryView) {
        setDependancies((BaseActivity) iGalleryView, R.layout.activity_gallery);
        setGrid(null);
    }

    @Override
    public void configureActionBar() {
        ActionBar ab = basicActionBarConfiguration(activity.getString(R.string.Gallery));
        ab.setDisplayUseLogoEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);

        /*ab.setTitle(getResources().getString(R.string.app_name));
        ab.setDisplayHomeAsUpEnabled(true);*/
    }


    private void setGridItems(int layout) {
        imageGridlayout = (GridView)activity.findViewById(R.id.imageGrid);
        ArrayAdapter imgGridAdapter = new GalleryImageAdapter(context, imageGridlayout, layout, getItems(), getColumnWidth());
        setImageGridItems(imgGridAdapter);
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
        item3.add(R.drawable.bg1);

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

    private void setImageGridItems(ArrayAdapter adapter) {
        imageGridlayout.setAdapter(adapter);
    }

    public void setInline(View view) {
        if(imageGridlayout.getNumColumns() == 1)
            return;

        setGridItems(R.layout.image_gallery_view_for_inline);
        imageGridlayout.setNumColumns(1);
        imageGridlayout.setColumnWidth((int)getScreenWidth());
    }

    public void setGrid(View view) {
        if(imageGridlayout != null && imageGridlayout.getNumColumns() == GridView.AUTO_FIT)
            return;

        setGridItems(R.layout.image_gallery_view_for_grid);
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
        largeImage.setImageResource(currentImage.getImageAlpha());
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
}
