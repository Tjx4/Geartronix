package com.emgr.geartronix.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class HomeTileAdapter extends ArrayAdapter {

    private final Activity activity;
    private final int layout;
    private final List<ArrayList> items;

    public HomeTileAdapter(@LayoutRes int resource, Activity activity, int layout, List<ArrayList> items) {
        super(activity, resource, items);
        this.activity = activity;
        this.layout = layout;
        this.items = items;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = activity.getLayoutInflater();
        View  parentView = li.inflate(layout, parent, false);

        return parentView;

     }


}
