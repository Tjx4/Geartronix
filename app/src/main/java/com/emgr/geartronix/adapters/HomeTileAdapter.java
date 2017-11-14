package com.emgr.geartronix.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.emgr.geartronix.R;
import java.util.ArrayList;
import java.util.List;

public class HomeTileAdapter extends ArrayAdapter {

    private final Activity activity;
    private final int layout;
    private final List<ArrayList> items;

    public HomeTileAdapter(Activity activity, int layout, List<ArrayList> items) {
        super(activity, layout, items);
        this.activity = activity;
        this.layout = layout;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = activity.getLayoutInflater();
        View  parentView = li.inflate(layout, parent, false);

        ImageView itemIcon = (ImageView)parentView.findViewById(R.id.imgHomeTile);
        itemIcon.setImageResource((int)items.get(position).get(0));

        TextView itemText = (TextView)parentView.findViewById(R.id.txtHomeTile);
        itemText.setText(items.get(position).get(1).toString());

        return parentView;

     }


}
