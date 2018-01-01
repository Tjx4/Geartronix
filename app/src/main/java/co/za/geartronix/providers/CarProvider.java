package co.za.geartronix.providers;

import android.graphics.Bitmap;
import java.util.Date;

public class CarProvider {

    private String make, model;
    private Date modelYear;
    private String color;
    private Bitmap picture;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getModelYear() {
        return modelYear;
    }

    public void setModelYear(Date modelYear) {
        this.modelYear = modelYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
