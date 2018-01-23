package co.za.geartronix.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class GalleryModel extends BaseModel {

    private List<ImageModel> images;

    @Override
    public void setModel(JSONObject responseJson) throws JSONException {
        super.setModel(responseJson);
        JSONArray itemsArray = responseJson.getJSONArray("images");

        images = new ArrayList<>();
        for(int i = 0; i < itemsArray.length(); i++){

            JSONObject itemsAr = (JSONObject)itemsArray.get(i);

            ImageModel currImage = new ImageModel();
            currImage.setId(itemsAr.getInt("id"));
            currImage.setPath(itemsAr.getString("path"));
            currImage.setDescription(itemsAr.getString("description"));

           images.add(currImage);
        }
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

}