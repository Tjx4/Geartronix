package co.za.geartronix.providers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.models.GalleryModel;
public class CacheProvider {

    private BaseActivity activity;
    private GalleryModel gallery;
    public static final String GALLERY_IMAGES = "GALLERY_IMAGES";
    SharedPreferences sharedPreferences;

    public CacheProvider(BaseActivity activity) {
        this.activity = activity;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    public GalleryModel getGalleryImages() throws JSONException {
        String js = sharedPreferences.getString(GALLERY_IMAGES, "");
        JSONObject connectionsJSONString = new JSONObject(js);
        GalleryModel cm = new GalleryModel();
        cm.setModel(connectionsJSONString);

        return cm;
    }

    public void updateGallery(GalleryModel gallery){
        if(gallery == null)
            return;

        this.gallery = gallery;
        //activity.getPreferences(MODE_PRIVATE).edit();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String connectionsJSONString = new Gson().toJson(gallery);
        editor.putString(GALLERY_IMAGES, connectionsJSONString);
        editor.commit();
    }
    
}