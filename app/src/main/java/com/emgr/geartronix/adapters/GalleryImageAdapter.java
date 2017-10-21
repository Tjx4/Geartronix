package com.emgr.geartronix.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.emgr.geartronix.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryImageAdapter extends ArrayAdapter{

    private final Activity activity;
    private final int layout;
    private final List<ArrayList> images;
    private final GridView parentGridview;
    private final float colWidth;

    public GalleryImageAdapter(Context context, GridView parentGridview,int layout, List images, float colWidth) {
        super(context, layout, images);

        activity = (Activity)context;
        this.layout = layout;
        this.images = images;
        this.parentGridview = parentGridview;
        this.colWidth = colWidth;
    }

    private View parentView;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater li = activity.getLayoutInflater();
            parentView  = li.inflate(layout, parent, false);

            setItemWidthRelativeToscreen();

            String itemName = String.valueOf(images.get(position).get(0));
            TextView name = (TextView)parentView.findViewById(R.id.txtName);
            name.setText(itemName);

            String itemDescription = String.valueOf(images.get(position).get(1));
            TextView description = (TextView)parentView.findViewById(R.id.txtDescription);
            description.setText(itemDescription);

            int itemImage = (int)images.get(position).get(2);
            ImageView image = (ImageView)parentView.findViewById(R.id.imgTheImage);
            image.setImageResource(itemImage);

        } else {
            parentView = (View) convertView;
        }

        return parentView;
    }

    private void setItemWidthRelativeToscreen() {
        if(parentGridview.getNumColumns() < 2)
            return;

        parentView.getLayoutParams().width = (int)colWidth;
        parentView.getLayoutParams().height = (int)colWidth;
        parentView.requestLayout();
    }
}
