package com.emgr.geartronix.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.emgr.geartronix.R;
import com.emgr.geartronix.providers.HttpConnectionProvider;
import java.util.ArrayList;
import java.util.List;

public class GalleryImageAdapter extends ArrayAdapter{

    private final Activity activity;
    private final int layout;
    private final List<ArrayList> items;

    public GalleryImageAdapter(Context context, int layout, List items) {
        super(context, layout, items);

        activity = (Activity)context;
        this.layout = layout;
        this.items = items;
    }

    private View parentView;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = activity.getLayoutInflater();
        parentView  = li.inflate(layout, parent, false);

        ProgressBar imageloading =  parentView.findViewById(R.id.progressBarSub);
        ImageButton image = parentView.findViewById(R.id.imgPic);

        new HttpConnectionProvider().setRemoteBitmap(imageloading, image, items.get(position).get(1).toString());

        TextView description = parentView.findViewById(R.id.txtDescription);
        description.setText(items.get(position).get(2).toString());

        return parentView;
    }

}