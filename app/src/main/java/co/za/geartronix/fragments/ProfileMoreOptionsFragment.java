package co.za.geartronix.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.za.geartronix.R;
import co.za.geartronix.activities.ProfileActivity;
import co.za.geartronix.providers.DialogFragmentProvider;

public class ProfileMoreOptionsFragment extends DialogFragmentProvider {

    ProfileActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = super.onCreateView( inflater,  container, savedInstanceState);

        View viewCarsLbl = parentView.findViewById(R.id.itemViewCars);
        View viewMessagesLbl = parentView.findViewById(R.id.itemViewMessages);

        setViewClickEvents(new View[]{viewCarsLbl, viewMessagesLbl});
        return parentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ProfileActivity) context;
    }

    @Override
    protected void onFragmentViewClickedEvent(View view) {
        activity.getPresenter().handleViewClickedEvent(view);
    }

}