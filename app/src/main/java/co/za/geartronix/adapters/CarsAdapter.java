package co.za.geartronix.adapters;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.za.geartronix.R;
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
        String name = carModel.getMake() +", "+carModel.getModel();
        String number = ": "+carModel.getNumber();
        String milage = carModel.getMilage();
        String modelYear = "2012"; //carModel.getModelYear().toString();
        String color = carModel.getColor();
        Bitmap picture = carModel.getPicture();

        TextView milageTxt = parentView.findViewById(R.id.txtMilageTxt);
        milageTxt.setText(milage);

        TextView nameTxt = parentView.findViewById(R.id.txtCarName);
        nameTxt.setText(name);

        TextView numberTxt = parentView.findViewById(R.id.txtCarNumber);
        numberTxt.setText(number);

        TextView modelYearTxt = parentView.findViewById(R.id.txtModelYear);
        modelYearTxt.setText(modelYear);

        TextView colorTxt = parentView.findViewById(R.id.txtColor);
        colorTxt.setText(color);

        ImageView pictureImg = parentView.findViewById(R.id.imgPicture);
        pictureImg.setImageBitmap(picture);

        return parentView;
    }
}