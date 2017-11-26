package com.emgr.geartronix.models;

import java.util.ArrayList;
import java.util.List;

public class GalleryModel  extends BaseModel {

    private List<ArrayList> items;
    private List item;
    private String imagePath;
    private int imageId;
    private String imageDescription;

    public List<ArrayList> getItems() {
        return items;
    }

    public void setItems(List<ArrayList> items) {
        this.items = items;
    }

    public List getItem() {
        return item;
    }

    public void setItem(List item) {
        this.item = item;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }
}