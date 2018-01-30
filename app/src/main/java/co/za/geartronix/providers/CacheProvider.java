package co.za.geartronix.providers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.models.GalleryModel;
import co.za.geartronix.models.ServicesListModel;

public class CacheProvider {

    private BaseActivity activity;
    private GalleryModel galleryModel;
    private ServicesListModel servicesListModel;
    public static final String GALLERY_IMAGES = "GALLERY_IMAGES";
    public static final String SERVICES = "SERVICES";
    SharedPreferences sharedPreferences;

    public CacheProvider(BaseActivity activity) {
        this.activity = activity;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    public GalleryModel getGalleryImages() throws JSONException {

        try {
            String js = sharedPreferences.getString(GALLERY_IMAGES, "");
            JSONObject connectionsJSONString = new JSONObject(js);
            GalleryModel galleryModel = new GalleryModel();
            galleryModel.setModel(connectionsJSONString);

            return galleryModel;
        }
        catch (Exception e) {
            return null;
        }
    }

    public ServicesListModel getServices() throws JSONException {

        try {
            String js = sharedPreferences.getString(SERVICES, "");
            JSONObject connectionsJSONString = new JSONObject(js);
            ServicesListModel servicesListModel = new ServicesListModel();
            servicesListModel.setModel(connectionsJSONString);

            return servicesListModel;
        }
        catch (Exception e) {
            return null;
        }
    }

    public void updateGallery(GalleryModel gallery){
        if(gallery == null)
            return;

        this.galleryModel = gallery;
        //activity.getPreferences(MODE_PRIVATE).edit();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String connectionsJSONString = new Gson().toJson(gallery);
        editor.putString(GALLERY_IMAGES, connectionsJSONString);
        editor.commit();
    }

    public void updateServices(ServicesListModel servicesListModel){
        if(servicesListModel == null)
            return;

        this.servicesListModel = servicesListModel;
        //activity.getPreferences(MODE_PRIVATE).edit();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String connectionsJSONString = new Gson().toJson(servicesListModel);
        editor.putString(SERVICES, connectionsJSONString);
        editor.commit();
    }
    
}