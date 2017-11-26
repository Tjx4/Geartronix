package com.emgr.geartronix.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class GalleryImageAdapter extends ArrayAdapter{

    private final Activity activity;
    private final int layout;
    private final List<ArrayList> items;
    private final float colWidth;

    public GalleryImageAdapter(Context context, int layout, List items, float colWidth) {
        super(context, layout, items);

        activity = (Activity)context;
        this.layout = layout;
        this.items = items;
        this.colWidth = colWidth;
    }

    private View parentView;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = activity.getLayoutInflater();
        parentView  = li.inflate(layout, parent, false);

        return parentView;
    }

}
