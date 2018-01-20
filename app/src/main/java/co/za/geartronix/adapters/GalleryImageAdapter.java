package co.za.geartronix.adapters;

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
import co.za.geartronix.R;
import co.za.geartronix.models.ImageModel;
import co.za.geartronix.providers.HttpConnectionProvider;
import java.util.List;

public class GalleryImageAdapter extends ArrayAdapter{

    private final Activity activity;
    private final int layout;
    private final List<ImageModel> items;

    public GalleryImageAdapter(Context context, int layout, List<ImageModel> images) {
        super(context, layout, images);

        activity = (Activity)context;
        this.layout = layout;
        this.items = images;
    }

    private View parentView;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = activity.getLayoutInflater();
        parentView  = li.inflate(layout, parent, false);

        ProgressBar imageloading =  parentView.findViewById(R.id.progressBarSub);
        ImageButton image = parentView.findViewById(R.id.imgPic);

        String path = items.get(position).getPath();
        image.setTag(path);

        new HttpConnectionProvider().setRemoteBitmap(imageloading, image, path);

        TextView description = parentView.findViewById(R.id.txtDescription);
        description.setText(items.get(position).getDescription());

        return parentView;
    }

}