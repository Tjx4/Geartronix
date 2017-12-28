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
import com.emgr.geartronix.activities.BaseActivity;
import com.emgr.geartronix.constants.Constants;
import com.emgr.geartronix.presenters.BaseAppActivityPresenter;
import com.emgr.geartronix.presenters.FindUsPresenter;
import com.emgr.geartronix.presenters.GalleryPresenter;
import com.emgr.geartronix.presenters.IHomePresenter;
import com.emgr.geartronix.presenters.MessagesPresenter;
import com.emgr.geartronix.presenters.ProfilePresenter;
import com.emgr.geartronix.presenters.ServicesPresenter;
import com.emgr.geartronix.views.IBaseView;
import com.emgr.geartronix.views.IHomeView;

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

    private int viewId;
    private BaseAppActivityPresenter currentAppActivity;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = activity.getLayoutInflater();
        View parentView = li.inflate(layout, parent, false);

        ImageView itemIcon = parentView.findViewById(R.id.imgHomeTile);
        itemIcon.setImageResource((int)items.get(position).get(0));

        TextView itemText = parentView.findViewById(R.id.txtHomeTile);
        itemText.setText(items.get(position).get(1).toString());
        parentView.setId(generateId(position));

        return parentView;
     }

    public BaseAppActivityPresenter generateAppActivity(int position) {
        switch (position)
        {
            case Constants.PROFILEID:
                currentAppActivity = new ProfilePresenter((BaseActivity)activity, position);
            break;
            case Constants.BOOKSERVICEID:
                currentAppActivity = new ServicesPresenter((BaseActivity) activity, position);
            break;
            case Constants.GALLERYID:
                currentAppActivity = new GalleryPresenter((BaseActivity)activity, position);
            break;
            case Constants.FINDUSID:
                currentAppActivity = new FindUsPresenter((BaseActivity)activity, position);
            break;
            case Constants.MESSAGES:
                currentAppActivity = new MessagesPresenter((BaseActivity)activity, position);
            break;
            case Constants.DIAGNOSTICS:
                currentAppActivity = new DiagnosticsPresenter((BaseActivity)activity, position);
            break;
        }

        return currentAppActivity;
    }

    private int generateId(int position) {
        switch (position)
        {
            case 0:
                viewId = Constants.PROFILEID;
            break;
            case 1:
                viewId = Constants.BOOKSERVICEID;
            break;
            case 2:
                viewId = Constants.GALLERYID;
            break;
            case 3:
                viewId = Constants.FINDUSID;
            break;
            case 4:
                viewId = Constants.MESSAGES;
            break;
            case 5:
                viewId = Constants.DIAGNOSTICS;
            break;
        }

        return viewId;
    }


}
