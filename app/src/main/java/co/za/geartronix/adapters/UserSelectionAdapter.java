package co.za.geartronix.adapters;

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
import co.za.geartronix.models.UserModel;

public class UserSelectionAdapter extends ArrayAdapter {

    private final BaseActivity activity;
    private final int layout;
    private final List<UserModel> users;

    public UserSelectionAdapter(BaseActivity activity, int layout, List<UserModel> users) {
        super(activity, layout, users);
        this.activity = activity;
        this.layout = layout;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = activity.getLayoutInflater();
        View parentView = li.inflate(layout, parent, false);

        UserModel currentUser = users.get(position);
        String username = currentUser.getNames().getFirstName();

        TextView icon = parentView.findViewById(R.id.txtUserinfo);
        icon.setId(currentUser.getId());

        TextView usernameTxt = parentView.findViewById(R.id.txtUsername);
        usernameTxt.setText(username);


        return parentView;
    }

}
