package co.za.geartronix.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.models.CarModel;

public class CarsAdapter  extends ArrayAdapter {

    private final BaseActivity activity;
    private final List<CarModel> cars;
    private final int layout;

    public CarsAdapter(BaseActivity activity, int layout, List<CarModel> cars) {
        super(activity, layout, cars);
        this.activity = activity;
        this.layout = layout;
        this.cars = cars;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = activity.getLayoutInflater();
        View parentView = li.inflate(layout, parent, false);

        CarModel carModel = cars.get(position);

        return parentView;
    }
}