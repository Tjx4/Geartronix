package co.za.geartronix.models;

import android.graphics.Bitmap;
import java.util.Date;

public class CarModel {

    private String make;
    private String model;

    public String getMilege() {
        return milege;
    }

    public void setMilege(String milege) {
        this.milege = milege;
    }

    private String milege;
    private Date modelYear;
    private String color;
    private Bitmap picture;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;

    public String getMilage() {
        return milage;
    }

    public void setMilage(String milage) {
        this.milage = milage;
    }

    private String milage;

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
