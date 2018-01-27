package co.za.geartronix.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.models.ServiceModel;

public class ServicesAdapter extends ArrayAdapter{

    private final BaseActivity activity;
    private final List<ServiceModel> services;
    private final int layout;

    public ServicesAdapter(BaseActivity activity, int layout, List<ServiceModel> services) {
        super(activity, layout, services);
        this.activity = activity;
        this.layout = layout;
        this.services = services;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = activity.getLayoutInflater();
        View parentView = li.inflate(layout, parent, false);

        ServiceModel currentService = services.get(position);
        int serviceId = currentService.getId();

        String service = currentService.getService();
        String serviceDescription = currentService.getServiceDescription();

        TextView serviceTxt = parentView.findViewById(R.id.txtService);
        serviceTxt.setText(service);

        TextView serviceDescriptionTxt = parentView.findViewById(R.id.txtDescription);
        serviceDescriptionTxt.setText(serviceDescription);

        parentView.setId(serviceId);

        return parentView;
    }
}
