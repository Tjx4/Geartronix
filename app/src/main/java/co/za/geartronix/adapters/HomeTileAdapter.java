package co.za.geartronix.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.constants.Constants;
import co.za.geartronix.presenters.BaseAppActivityPresenter;
import co.za.geartronix.presenters.DiagnosticsPresenter;
import co.za.geartronix.presenters.FindUsPresenter;
import co.za.geartronix.presenters.GalleryPresenter;
import co.za.geartronix.presenters.MessagesPresenter;
import co.za.geartronix.presenters.ProfilePresenter;
import co.za.geartronix.presenters.ServicesPresenter;
import java.util.List;

public class HomeTileAdapter extends ArrayAdapter {

    private final BaseActivity activity;
    private final int layout;
    private final List<BaseAppActivityPresenter> items;

    public HomeTileAdapter(BaseActivity activity, int layout, List<BaseAppActivityPresenter> items) {
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

        BaseAppActivityPresenter appActivity = items.get(position);

        ImageView itemIcon = parentView.findViewById(R.id.imgHomeTile);
        itemIcon.setImageResource(appActivity.getIcon());

        TextView itemText = parentView.findViewById(R.id.txtHomeTile);
        itemText.setText(appActivity.getDisplayName());
        parentView.setId(generateId(position));

        int priorityColor = Color.parseColor("#FF01689B");
        if(appActivity.getClass() == ServicesPresenter.class || appActivity.getClass() == DiagnosticsPresenter.class )
            parentView.setBackgroundColor(priorityColor);

        return parentView;
     }

    public BaseAppActivityPresenter generateAppActivity(int position) {
        switch (position)
        {
            case Constants.PROFILEID:
                currentAppActivity = new ProfilePresenter(activity, position);
            break;
            case Constants.BOOKSERVICEID:
                currentAppActivity = new ServicesPresenter(activity, position);
            break;
            case Constants.GALLERYID:
                currentAppActivity = new GalleryPresenter(activity, position);
            break;
            case Constants.FINDUSID:
                currentAppActivity = new FindUsPresenter(activity, position);
            break;
            case Constants.MESSAGES:
                currentAppActivity = new MessagesPresenter(activity, position);
            break;
            case Constants.DIAGNOSTICS:
                currentAppActivity = new DiagnosticsPresenter(activity, position);
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
