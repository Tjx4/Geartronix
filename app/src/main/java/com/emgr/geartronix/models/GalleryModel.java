package com.emgr.geartronix.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GalleryModel  extends BaseModel {

    private List<ArrayList> items;
    private List item;
    private String imagePath;
    private int imageId;
    private String imageDescription;

    @Override
    public void setModel(JSONObject response) throws JSONException {
        super.setModel(response);
        JSONArray itemsArray = response.getJSONArray("items");

        items = new ArrayList<>();
        for(int i = 0; i < itemsArray.length(); i++){

            JSONObject itemsAr = (JSONObject)itemsArray.get(i);

            ArrayList al = new ArrayList();
            al.add(itemsAr.getInt("id"));
            al.add(itemsAr.getString("path"));
            al.add(itemsAr.getString("description"));

            items.add(al);
        }


    }
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