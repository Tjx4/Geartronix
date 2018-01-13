package co.za.geartronix.providers;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.za.geartronix.constants.Constants;

public class DialogFragmentProvider extends DialogFragment {

   static DialogFragmentProvider newInstance() {
        return new DialogFragmentProvider();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(""); //getArguments().getString(Constants.TITLE));
        int layout = getArguments().getInt(Constants.LAYOUT);
        View v = inflater.inflate(layout, container, false);

        return v;
    }

    protected void setViewClickEvents(View[] views) {
        for(View view : views){
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onFragmentViewClickedEvent(v);
                }
            });
        }
    }

    protected void onFragmentViewClickedEvent(View view) {
    }

}